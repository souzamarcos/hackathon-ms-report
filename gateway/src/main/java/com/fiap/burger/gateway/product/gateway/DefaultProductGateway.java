package com.fiap.burger.gateway.product.gateway;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.gateway.product.dao.ProductDAO;
import com.fiap.burger.gateway.product.model.ProductJPA;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DefaultProductGateway implements ProductGateway {
    @Autowired
    ProductDAO productDAO;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product findById(Long id) {
        return productDAO.findById(id).map(ProductJPA::toEntity).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAllByDeletedAtNull().stream().map(ProductJPA::toEntity).toList();
    }

    @Override
    public List<Product> findAllBy(Category category, List<Long> id) {
        String qlString = "select p FROM ProductJPA p Where deletedAt = null";

        if (category != null) {
            qlString += " and p.category = " + category.name();
        }

        if (id != null) {
            qlString += " and p.id in (" + id.stream().map(Object::toString).collect(Collectors.joining(",")) + ")";
        }

        return entityManager.createQuery(qlString, ProductJPA.class)
            .getResultList()
            .stream()
            .map(ProductJPA::toEntity)
            .toList();
    }

    @Override
    public Product save(Product product) {
        return productDAO.save(ProductJPA.toJPA(product)).toEntity();
    }

    @Override
    public void deleteBy(Long id) {
        productDAO.deleteById(id);
    }
}

