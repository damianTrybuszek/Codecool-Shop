package com.codecool.shop.user;

import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;

public class Order {
  private final int customerId;
  private HashMap<Product, Integer> cart;

  public Order(int customerId) {
    this.customerId = customerId;
    cart = new HashMap<>();
  }

  public void addProduct(Product product) {
    if (cart.containsKey(product)) {
      cart.put(product, cart.get(product) + 1);
    } else {
      cart.put(product, 1);
    }
  }

  public Product getProduct(int id) {
    for (Product product : cart.keySet()) {
      if (product.getId() == id)
        return product;
    }
    return null;
  }

  public void removeProduct(Product productToRemove) {
    cart.remove(productToRemove);
  }

  public int getDifferentItemsInCartQuantity() {
    return cart.size();
  }

  public int getAllItemsInCartQuantity() {
    int allItemsQuantity = 0;
    for (Integer quantity : cart.values())
      allItemsQuantity += quantity;

    return allItemsQuantity;
  }

  public BigDecimal getCartValue() {
    BigDecimal cartValue = new BigDecimal(0);
    for (Product product : cart.keySet())
      cartValue = cartValue.add(product.getDefaultPrice().multiply(new BigDecimal(cart.get(product))));

    return cartValue;
  }
}
