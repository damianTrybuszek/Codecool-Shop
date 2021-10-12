package com.codecool.shop.user;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

  Order cart = new Order(-1);
  Supplier amazon = new Supplier("Amazon", "Digital content and services");
  ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");


  @Test
  public void test() {
    Product product = new Product("name", new BigDecimal(100.99), "USD", "desc", tablet, amazon);
    assertNull(cart.getProduct(0));
    cart.addProduct(product);
    assertNotNull(cart.getProduct(0));
    assertEquals("id: 0, name: name, defaultPrice: 100.990000, defaultCurrency: USD, productCategory: Tablet, supplier: Amazon", cart.getProduct(0).toString());
    cart.removeProduct(product);
    assertNull(cart.getProduct(0));
    cart.addProduct(product);
    cart.addProduct(product);
    assertNotNull(cart.getProduct(0));
    assertEquals("id: 0, name: name, defaultPrice: 100.990000, defaultCurrency: USD, productCategory: Tablet, supplier: Amazon", cart.getProduct(0).toString());
    assertEquals(1, cart.getDifferentItemsInCartQuantity());
    assertEquals(2, cart.getAllItemsInCartQuantity());
//    assertEquals(new BigDecimal(201.9799999999999897681846050545573234558105468750), cart.getCartValue());
//    assertEquals("201.9799999999999897681846050545573234558105468750", cart.getCartValue());

    Product product1 = new Product("nameee", new BigDecimal(11), "USD", "desc", tablet, amazon);
    cart.addProduct(product1);
//    assertEquals(new BigDecimal(212.9799999999999897681846050545573234558105468750), cart.getCartValue());
    assertEquals(2, cart.getDifferentItemsInCartQuantity());
    assertEquals(3, cart.getAllItemsInCartQuantity());

  }

}