package com.codecool.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductQuantity {
    private Product product;
    private int quantity;

}
