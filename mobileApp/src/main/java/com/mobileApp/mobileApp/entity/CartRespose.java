package com.mobileApp.mobileApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRespose {

    List<CartProductModel> products;
    double total;

    public List<CartProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<CartProductModel> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
