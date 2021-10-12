package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoMemTest {

  private static List<Product> data = new ArrayList<>();

  @BeforeAll
  public static void setUpData(){
    Supplier amazon = new Supplier("Amazon", "Digital content and services");
    Supplier lenovo = new Supplier("Lenovo", "Computers");
    Supplier samsung = new Supplier("Samsung", "Computers, home appliances and electronics");

    Supplier dell = new Supplier("Dell", "Computers");
    Supplier apple = new Supplier("Apple", "Computers, home appliances and electronics, smartphones, smartwatches");

    ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
    ProductCategory monitor = new ProductCategory("Monitor", "Hardware", "Output device for direct communication between user and computer. The purpose of the monitor is to immediately visualize the results of programs running on the computer.");
    ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop, laptop computer, or notebook computer is a small, portable personal computer (PC) with a screen and alphanumeric keyboard.");

    data.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
    data.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
    data.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
    data.add(new Product("Samsung CF390 27", new BigDecimal("120.9"), "USD", "Ultra curved screen for wide viewing area and image depth. Eye comfort - thanks to flicker free technology and eye saver mode.", monitor, samsung));
    data.add(new Product("Apple MacBook Pro 13 M1", new BigDecimal("1561.54"), "USD", "It was created for people with high expectations who do not tolerate defects and care about quality and elegance. If that's what you need, this device is the perfect product for you.", laptop, apple));
    data.add(new Product("Dell Latitude 5510", new BigDecimal("1216.18"), "USD", "Laptop Dell Latitude 5510 i5-10210U | 15,6\" FHD | 8GB | 256GB SSD | Int | Windows 10 Pro (S001L551015PL)", laptop, dell));
    data.add(new Product("Apple2 MacBook Pro 13 M1", new BigDecimal("1561.54"), "USD", "It was created for people with high expectations who do not tolerate defects and care about quality and elegance. If that's what you need, this device is the perfect product for you.", laptop, apple));
    data.add(new Product("Apple3 MacBook Pro 13 M1", new BigDecimal("1561.54"), "USD", "It was created for people with high expectations who do not tolerate defects and care about quality and elegance. If that's what you need, this device is the perfect product for you.", laptop, apple));
  }

  @Test
  public void test(){
    System.out.println(data.toString());
    System.out.println(data.get(0));
    System.out.println(data.get(0).getName().toUpperCase());
    System.out.println(data.get(0).hasPhrase("qwer") + " qwer");
    System.out.println(data.get(0).hasPhrase("maz") + " maz");
    System.out.println(data.get(0).hasPhrase("Fire") + " Fire");
    System.out.println(data.get(0).hasPhrase("qwerasd") + " qwerasd");
    System.out.println(data.get(0).hasPhrase("fire") + " fire");
    System.out.println(data.get(0).hasPhrase("table") + " table");
    System.out.println(data.get(0).hasPhrase("Am") + " Am");

    System.out.println("----------------------------");
    ProductDaoMem pdm = ProductDaoMem.getInstance();
    for (Product product : data)
      pdm.add(product);
    System.out.println("qwer: " + pdm.search("qwer"));
    System.out.println("Fire: " + pdm.search("Fire"));
    System.out.println("pRo: " + pdm.search("pRo"));
    System.out.println("idea: " + pdm.search("idea"));
    System.out.println("apple: " + pdm.search("apple"));
    System.out.println("laptop: " + pdm.search("laptop"));
  }
}