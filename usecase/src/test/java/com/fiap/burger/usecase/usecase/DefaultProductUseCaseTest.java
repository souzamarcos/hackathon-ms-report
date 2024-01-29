package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import com.fiap.burger.usecase.misc.ProductBuilder;
import com.fiap.burger.usecase.misc.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DefaultProductUseCaseTest {

    @Mock
    ProductGateway gateway;

    @InjectMocks
    DefaultProductUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class findById {
        @Test
        void shouldFindById() {
            var id = 1L;
            var expected = new ProductBuilder().withId(1L).build();

            when(gateway.findById(id)).thenReturn(expected);

            var actual = useCase.findById(id);

            assertEquals(expected, actual);

            verify(gateway, times(1)).findById(id);
        }
    }

    @Nested
    class findAll {
        @Test
        void shouldFindAllProducts() {
            List<Product> expected = Arrays.asList(new ProductBuilder().withId(1L).build(), new ProductBuilder().withId(2L).build());

            when(gateway.findAll()).thenReturn(expected);

            List<Product> actual = useCase.findAll();

            assertEquals(expected, actual);

            verify(gateway, times(1)).findAll();
        }

        @Test
        void shouldFindAllProductsByCategory() {
            List<Product> expected = Collections.singletonList(new ProductBuilder().build());
            Category category = Category.LANCHE;

            when(gateway.findAllBy(category, null)).thenReturn(expected);

            List<Product> actual = useCase.findAllBy(category, null);

            assertEquals(expected, actual);

            verify(gateway, times(1)).findAllBy(category, null);
        }

        @Test
        void shouldFindAllProductsByIds() {
            List<Product> expected = new ArrayList<>();
            expected.add(new ProductBuilder().withId(1L).build());
            expected.add(new ProductBuilder().withId(2L).build());

            when(gateway.findAllBy(null, List.of(1L, 2L))).thenReturn(expected);

            List<Product> actual = useCase.findAllBy(null, List.of(1L, 2L));

            assertEquals(expected, actual);

            verify(gateway, times(1)).findAllBy(null, List.of(1L, 2L));
        }
    }

    @Nested
    class saveProduct {
        @Test
        void shouldSaveProduct() {
            Product product = new ProductBuilder().withId(null).build();

            when(gateway.save(product)).thenReturn(product);

            Product actual = useCase.insert(product);

            assertEquals(product, actual);

            verify(gateway, times(1)).save(product);
        }

        @Test
        void shouldThrowInvalidAttributeExceptionWhenProductIdIsNotNullToInsert() {
            Product product = new ProductBuilder().withId(1L).build();

            assertThrows(InvalidAttributeException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }

        @Test
        void shouldThrowNullAttributeExceptionWhenProductCategoryIsNullToInsert() {
            Product product = new ProductBuilder().withId(null).withCategory(null).build();

            assertThrows(NullAttributeException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }

        @Test
        void shouldThrowNullAttributeExceptionWhenProductNameIsNullToInsert() {
            Product product = new ProductBuilder().withId(null).withName(null).build();

            assertThrows(NullAttributeException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }

        @Test
        void shouldThrowBlankAttributeExceptionWhenProductNameIsBlankToInsert() {
            Product product = new ProductBuilder().withId(null).withName("  ").build();

            assertThrows(BlankAttributeException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }

        @Test
        void shouldThrowNullAttributeExceptionWhenProductDescriptionIsNullToInsert() {
            Product product = new ProductBuilder().withId(null).withDescription(null).build();

            assertThrows(NullAttributeException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }

        @Test
        void shouldThrowBlankAttributeExceptionWhenProductDescriptionIsBlankToInsert() {
            Product product = new ProductBuilder().withId(null).withDescription("  ").build();

            assertThrows(BlankAttributeException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }

        @Test
        void shouldThrowNullAttributeExceptionWhenProductPriceIsNullToInsert() {
            Product product = new ProductBuilder().withId(null).withPrice(null).build();

            assertThrows(NullAttributeException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }

        @Test
        void shouldThrowBlankAttributeExceptionWhenProductPriceIsLessThanZeroToInsert() {
            Product product = new ProductBuilder().withId(null).withPrice(-0.1).build();

            assertThrows(NegativeOrZeroValueException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }

        @Test
        void shouldThrowBlankAttributeExceptionWhenProductPriceIsZeroToInsert() {
            Product product = new ProductBuilder().withId(null).withPrice(0.0).build();

            assertThrows(NegativeOrZeroValueException.class, () -> useCase.insert(product));

            verify(gateway, times(0)).save(product);
        }
    }

    @Nested
    class updateProduct {
        @Test
        void shouldUpdateProduct() {
            var id = 1L;
            var product = new ProductBuilder().withId(1L).build();

            when(gateway.findById(id)).thenReturn(product);
            when(gateway.save(product)).thenReturn(product);

            var actual = useCase.update(product);

            assertEquals(product, actual);

            verify(gateway, times(1)).save(product);
        }

        @Test
        void shouldThrowProductNotFoundExceptionWhenProductToUpdateDontExist() {
            var id = 1L;
            var product = new ProductBuilder().withId(1L).build();

            when(gateway.findById(id)).thenReturn(null);

            assertThrows(ProductNotFoundException.class, () -> useCase.update(product));

            verify(gateway, never()).save(product);
        }
    }

    @Nested
    class deleteBy {
        @Test
        void shouldDeleteProduct() {
            var id = 1L;
            var product = new ProductBuilder().withId(1L).build();

            when(gateway.findById(id)).thenReturn(product);

            useCase.deleteBy(id);

            verify(gateway, times(1)).deleteBy(id);
        }

        @Test
        void shouldThrowProductExceptionWhenProductToDeleteDontExist() {
            var id = 1L;

            when(gateway.findById(id)).thenReturn(null);

            assertThrows(ProductNotFoundException.class, () -> useCase.deleteBy(id));

            verify(gateway, never()).deleteBy(id);
        }

        @Test
        void shouldThrowProductExceptionWhenProductToDeleteAlreadyExist() {
            var id = 1L;
            var product = new ProductBuilder().withId(1L).withDeletedAt(LocalDateTime.now()).build();

            when(gateway.findById(id)).thenReturn(product);

            assertThrows(DeletedProductException.class, () -> useCase.deleteBy(id));

            verify(gateway, never()).deleteBy(id);
        }
    }
}