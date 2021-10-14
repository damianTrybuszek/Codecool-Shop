package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CartDao {

    void add(Product product);
    void remove(Product product);
    void update(Product product);
    HashMap<String, String> getProductString();
    HashMap<String, String> getQuantityProd();


}
