package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import com.fiap.burger.usecase.misc.ProductBuilder;
import com.fiap.burger.usecase.misc.exception.BlankAttributeException;
import com.fiap.burger.usecase.misc.exception.InvalidAttributeException;
import com.fiap.burger.usecase.misc.exception.NegativeOrZeroValueException;
import com.fiap.burger.usecase.misc.exception.NullAttributeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @Test
    void shouldFindById() {
        var id = 1L;
        var expected = new ProductBuilder().withId(1L).build();

        when(gateway.findById(id)).thenReturn(expected);

        var actual = useCase.findById(id);

        assertEquals(expected, actual);

        verify(gateway, times(1)).findById(id);
    }

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
        Category category = Category.BEBIDA;

        when(gateway.findAllBy(category, null)).thenReturn(expected);

        List<Product> actual = useCase.findAllBy(category, null);

        assertEquals(expected, actual);

        verify(gateway, times(1)).findAllBy(category, null);
    }

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
    void shouldThrowNullAttributeExceptionWhenProductValueIsNullToInsert() {
        Product product = new ProductBuilder().withId(null).withValue(null).build();

        assertThrows(NullAttributeException.class, () -> useCase.insert(product));

        verify(gateway, times(0)).save(product);
    }

    @Test
    void shouldThrowBlankAttributeExceptionWhenProductValueIsLessThanZeroToInsert() {
        Product product = new ProductBuilder().withId(null).withValue(-0.1).build();

        assertThrows(NegativeOrZeroValueException.class, () -> useCase.insert(product));

        verify(gateway, times(0)).save(product);
    }

    @Test
    void shouldThrowBlankAttributeExceptionWhenProductValueIsZeroToInsert() {
        Product product = new ProductBuilder().withId(null).withValue(0.0).build();

        assertThrows(NegativeOrZeroValueException.class, () -> useCase.insert(product));

        verify(gateway, times(0)).save(product);
    }
}