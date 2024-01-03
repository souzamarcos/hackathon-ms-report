package com.fiap.burger.usecase.adapter.gateway;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;

import java.util.List;

public interface ProductGateway {
    Product findById(Long id);

    List<Product> findAll();

    List<Product> findAllBy(Category category, List<Long> ids);

    Product save(Product product);

    void deleteBy(Long id);
}
