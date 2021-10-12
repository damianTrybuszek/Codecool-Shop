package com.codecool.shop.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

public class Product extends BaseModel {
  private static int productsQuantity = 0;
  @Getter
  private int id;
  private BigDecimal defaultPrice;
  private Currency defaultCurrency;
  private ProductCategory productCategory;
  private Supplier supplier;


  public Product(String name, BigDecimal defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
    super(name, description);
    this.id = productsQuantity++;
    this.setPrice(defaultPrice, currencyString);
    this.setSupplier(supplier);
    this.setProductCategory(productCategory);
  }

  public void setPrice(BigDecimal price, String currency) {
    this.defaultPrice = price;
    this.defaultCurrency = Currency.getInstance(currency);
  }

  public BigDecimal getDefaultPrice() {
    return defaultPrice;
  }

  public void setDefaultPrice(BigDecimal defaultPrice) {
    this.defaultPrice = defaultPrice;
  }

  public Currency getDefaultCurrency() {
    return defaultCurrency;
  }

  public void setDefaultCurrency(Currency defaultCurrency) {
    this.defaultCurrency = defaultCurrency;
  }

  public String getPrice() {
    return this.defaultPrice + " " + this.defaultCurrency.toString();
  }

  public ProductCategory getProductCategory() {
    return productCategory;
  }

  public void setProductCategory(ProductCategory productCategory) {
    this.productCategory = productCategory;
    this.productCategory.addProduct(this);
  }

  public boolean hasPhrase(String searchPhrase) {
    return this.toString().toUpperCase().contains(searchPhrase.toUpperCase());
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
    this.supplier.addProduct(this);
  }

  @Override
  public String toString() {
    return String.format("id: %1$d, " +
            "name: %2$s, " +
            "defaultPrice: %3$f, " +
            "defaultCurrency: %4$s, " +
            "productCategory: %5$s, " +
            "supplier: %6$s",
        this.id,
        this.name,
        this.defaultPrice,
        this.defaultCurrency.toString(),
        this.productCategory.getName(),
        this.supplier.getName());
  }
}