package com.fiap.burger.entity.product;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void shouldCreateInstanceWithSimpleConstructor() {
        var category = Category.LANCHE;
        var name = "Product Test";
        var description = "Product description";
        var price = 22.2;

        Product actual = new Product(
            category,
            name,
            description,
            price
        );

        assertEquals(category, actual.getCategory());
        assertEquals(name, actual.getName());
        assertEquals(description, actual.getDescription());
        assertEquals(price, actual.getPrice());
    }

    @Test
    void shouldCreateInstanceWithSimpleConstructorWithId() {
        var id = 1L;
        var category = Category.LANCHE;
        var name = "Product Test";
        var description = "Product description";
        var price = 22.2;

        Product actual = new Product(
            id,
            category,
            name,
            description,
            price
        );

        assertEquals(id, actual.getId());
        assertEquals(category, actual.getCategory());
        assertEquals(name, actual.getName());
        assertEquals(description, actual.getDescription());
        assertEquals(price, actual.getPrice());
    }

    @Test
    void shouldCreateInstanceWithFullConstructor() {
        var id = 1L;
        var category = Category.LANCHE;
        var name = "Product Test";
        var description = "Product description";
        var price = 22.2;
        var createdAt = LocalDateTime.now();
        var modifiedAt = LocalDateTime.now();

        Product actual = new Product(
            id,
            category,
            name,
            description,
            price,
            createdAt,
            modifiedAt,
            null
        );

        assertEquals(id, actual.getId());
        assertEquals(category, actual.getCategory());
        assertEquals(name, actual.getName());
        assertEquals(description, actual.getDescription());
        assertEquals(price, actual.getPrice());
        assertEquals(price, actual.getPrice());
        assertEquals(createdAt, actual.getCreatedAt());
        assertEquals(modifiedAt, actual.getModifiedAt());
        assertEquals(null, actual.getDeletedAt());
    }
}
