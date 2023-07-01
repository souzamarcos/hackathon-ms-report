package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.repository.client.ClientRepository;
import com.fiap.burger.domain.adapter.repository.order.OrderRepository;
import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.entities.order.Order;
import com.fiap.burger.domain.entities.order.OrderItem;
import com.fiap.burger.domain.entities.order.OrderStatus;
import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.misc.exception.InvalidAttributeException;
import com.fiap.burger.domain.misc.exception.NegativeOrZeroValueException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }



    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order insert(Order order) {
        List<Long> productsId =  order.getItems().stream().map(OrderItem::getProductId).collect(Collectors.toList());
        productsId.addAll(order.getItems().stream().flatMap(item -> Optional.ofNullable(item.getAdditionalIds()).orElse(Collections.emptyList()).stream()).toList());
        List<Product> products = productRepository.findByIds(productsId);
        validateProducts(order, products);
        order.setTotal(calculateTotal(productsId, products));
        validateOrderToInsert(order);
        var persistedOrder = orderRepository.save(order);
        return persistedOrder;
    }

    private void validateProducts(Order order, List<Product> products) {
        order.getItems().stream().forEach(item -> {
            Optional<Product> itemProduct = products.stream().filter(product -> product.getId().equals(item.getProductId())).findFirst();
            if (itemProduct.isEmpty()) {
                throw new InvalidAttributeException("Product '" + item.getProductId() + "' not found.", "items.productId");
            }

            if (!validateCategory(itemProduct.get().getCategory(), false, !Optional.ofNullable(item.getAdditionalIds()).orElse(Collections.emptyList()).isEmpty())) {
                throw new InvalidAttributeException("Product '" + item.getProductId() + "' has invalid category for an item.'", "items.productId");
            }

            Optional.ofNullable(item.getAdditionalIds()).orElse(Collections.emptyList()).stream().forEach(additional -> {
                Optional<Product> additionalProduct = products.stream().filter(product -> product.getId().equals(additional)).findFirst();
                if (additionalProduct.isEmpty()) {
                    throw new InvalidAttributeException("Product '" + additional + "' not found.", "items.additionalIds");
                }

                if (!validateCategory(additionalProduct.get().getCategory(), true, false)) {
                    throw new InvalidAttributeException("Product '" + additional + "' has invalid category for an additional.'", "items.additionalIds");
                }
            });

        });
    }

    private Boolean validateCategory(Category category, Boolean isAdditional, Boolean hasAdditional) {
        List<Category> itemCategories = List.of(Category.LANCHE, Category.ACOMPANHAMENTO, Category.BEBIDA, Category.SOBREMESA);
        List<Category> additionalCategories = List.of(Category.ADICIONAL);
        List<Category> itemWithAdditionalsCategories = List.of(Category.LANCHE);
        if (isAdditional) {
            return additionalCategories.contains(category);
        } else {
            if (hasAdditional) {
                return itemWithAdditionalsCategories.contains(category);
            }
            return itemCategories.contains(category);
        }


    }


    private Double calculateTotal(List<Long> productIds, List<Product> products) {
        return productIds.stream().mapToDouble(id -> products.stream().filter(product -> product.getId().equals(id)).findFirst().map(Product::getValue).orElse(0.0)).sum();
    }

    // TODO refatorar validação e calculo de total pra ficar numa estrutura mais legível

    private Double calculateTotal(List<OrderItem> items) {
        AtomicReference<Double> itemsTotal = new AtomicReference<>(0.0);
        items.forEach(item -> {
            var product = productRepository.findById(item.getProductId());
            if (product == null) {
                throw new InvalidAttributeException("Product '" + item.getProductId() + "' not found.", "items.productId");
            }
            itemsTotal.updateAndGet(v -> v + product.getValue());
        });
        return itemsTotal.get();
    }

    private void validateOrderToInsert(Order order) {
        if (order.getClientId() != null) {
            var client = clientRepository.findById(order.getClientId());
            if (client == null) {
                throw new InvalidAttributeException("Client '" + order.getClientId() + "' not found.", "clientId");
            }
        }

        if (order.getStatus() != OrderStatus.RECEBIDO) {
            throw new InvalidAttributeException("Order should be created with status 'RECEBIDO'", "id");
        }
        validateOrder(order);
    }

    private void validateOrder(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty())
            throw new InvalidAttributeException("Order items should not be empty", "items");

        if (order.getTotal() <= 0) throw new NegativeOrZeroValueException("total");
    }
}
