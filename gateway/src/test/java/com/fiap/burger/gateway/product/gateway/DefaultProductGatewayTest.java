package com.fiap.burger.gateway.product.gateway;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.gateway.misc.ProductBuilder;
import com.fiap.burger.gateway.misc.ProductJPABuilder;
import com.fiap.burger.gateway.product.dao.ProductDAO;
import com.fiap.burger.gateway.product.model.ProductJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultProductGatewayTest {

    @Mock
    ProductDAO productDAO;

    @Mock
    EntityManager entityManager;

    @InjectMocks
    DefaultProductGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        var productJPA = new ProductJPABuilder().withId(1L).build();
        var expected = productJPA.toEntity();

        when(productDAO.findById(id)).thenReturn(Optional.of(productJPA));

        var actual = gateway.findById(id);

        assertEquals(expected, actual);

        verify(productDAO, times(1)).findById(id);
    }

    @Nested
    class findAll {
        @Test
        void shouldFindAllProducts() {
            var productsJPA = Arrays.asList(new ProductJPABuilder().withId(1L).build(), new ProductJPABuilder().withId(2L).build());
            var expected = productsJPA.stream().map(ProductJPA::toEntity).toList();

            when(productDAO.findAllByDeletedAtNull()).thenReturn(productsJPA);

            var actual = gateway.findAll();

            assertIterableEquals(expected, actual);

            verify(productDAO, times(1)).findAllByDeletedAtNull();
        }

        @Test
        void shouldFindAllProductsByCategory() {
            var category = Category.LANCHE;
            var productsJPA = Collections.singletonList(new ProductJPABuilder().withId(2L).build());
            var expected = productsJPA.stream().map(ProductJPA::toEntity).toList();

            TypedQuery<ProductJPA> query = (TypedQuery<ProductJPA>) mock(TypedQuery.class);
            when(entityManager.createQuery(anyString(), eq(ProductJPA.class))).thenReturn(query);
            when(query.getResultList()).thenReturn(productsJPA);

            var actual = gateway.findAllBy(category, null);

            assertIterableEquals(expected, actual);

            verify(entityManager, times(1)).createQuery(anyString(), eq(ProductJPA.class));
            verify(query, times(1)).getResultList();
        }

        @Test
        void shouldFindAllProductsByIds() {
            List<ProductJPA> productsJPA = new ArrayList<>();
            productsJPA.add(new ProductJPABuilder().withId(1L).build());
            productsJPA.add(new ProductJPABuilder().withId(2L).build());
            var expected = productsJPA.stream().map(ProductJPA::toEntity).toList();

            TypedQuery<ProductJPA> query = (TypedQuery<ProductJPA>) mock(TypedQuery.class);
            when(entityManager.createQuery(anyString(), eq(ProductJPA.class))).thenReturn(query);
            when(query.getResultList()).thenReturn(productsJPA);

            var actual = gateway.findAllBy(null, List.of(1L, 2L));

            assertIterableEquals(expected, actual);

            verify(entityManager, times(1)).createQuery(anyString(), eq(ProductJPA.class));
            verify(query, times(1)).getResultList();
        }
    }

    @Test
    void shouldSaveProduct() {
        var productJPA = new ProductJPABuilder().withId(1L).build();
        var product = new ProductBuilder().withId(null).build();
        var expected = new ProductBuilder().withId(1L).build();

        when(productDAO.save(any())).thenReturn(productJPA);

        var actual = gateway.save(product);

        assertEquals(expected.getId(), actual.getId());

        verify(productDAO, times(1)).save(any());
    }

    @Test
    void shouldDeleteProduct() {
        var id = 1L;

        gateway.deleteBy(id);

        verify(productDAO, times(1)).deleteById(1L);
    }
}