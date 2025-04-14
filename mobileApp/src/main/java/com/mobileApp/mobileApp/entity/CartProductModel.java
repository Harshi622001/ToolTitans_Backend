package com.mobileApp.mobileApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartProductModel {
    private ProductEntity product;
    private Integer orderedQuantity;

    public CartProductModel(ProductEntity product, Integer orderedQuantity) {
        this.product = product;
        this.orderedQuantity = orderedQuantity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }
}