package com.fiap.burger.application.utils;

import com.fiap.burger.api.dto.product.request.ProductInsertRequestDto;
import com.fiap.burger.api.dto.product.request.ProductUpdateRequestDto;
import com.fiap.burger.api.dto.product.response.ProductResponseDto;
import com.fiap.burger.entity.product.Category;

public class ProductHelper {

    public static ProductInsertRequestDto createProductRequest() {
        return new ProductInsertRequestDto(Category.LANCHE, "Nome do Produto", "Descrição do Produto", 10.0);
    }

    public static ProductUpdateRequestDto updateProductRequest(ProductResponseDto productResponse) {
        return new ProductUpdateRequestDto(productResponse.id(), productResponse.category(), productResponse.name(), productResponse.description(), productResponse.value());
    }

}
