package com.codecool.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
public class ProductQuantity {
    private int id;
    private String name;
    private BigDecimal defaultPrice;
    private String defaultCurrency;
    private String productCategory;
    private String supplier;
    private int quantity;

}
