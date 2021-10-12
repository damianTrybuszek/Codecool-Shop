package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends BaseModel {
  private static int categoryQuantity = 0;
  private int id;
  private String department;
  private List<Product> products;

  public ProductCategory(String name, String department, String description) {
    super(name);
    this.id = categoryQuantity++;
    this.department = department;
    this.products = new ArrayList<>();
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public List<Product> getProducts() {
    return this.products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }

  public void addProduct(Product product) {
    this.products.add(product);
  }

  @Override
  public String toString() {
    return String.format(
        "id: %1$d," +
            "name: %2$s, " +
            "department: %3$s, " +
            "description: %4$s",
        this.id,
        this.name,
        this.department,
        this.description);
  }
}