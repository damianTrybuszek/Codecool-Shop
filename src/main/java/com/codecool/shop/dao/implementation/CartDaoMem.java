package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {
    private Map<Product, Integer> cart = new HashMap<>();
    private static CartDaoMem instance = null;

    private CartDaoMem(){}

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }
    @Override
    public void add(Product product) {

        for(Product prod: cart.keySet()) {
            if(product.equals(prod)){
                int numberOfItem = cart.get(prod);
                cart.replace(prod, numberOfItem + 1);
            } else {
                cart.put(product, 1);
            }
        }
    }

    @Override
    public void remove(Product product) {

    }

    @Override
    public void update(Product product) {

    }
    public Map<Product, Integer> getCart(){
        return this.cart;
    }

    public int countProduct() {
        int counter = 0;

        if(!cart.isEmpty()) {
            for (Product prod : cart.keySet()) {
                counter++;
            }
        }
        return counter;
    }
}
