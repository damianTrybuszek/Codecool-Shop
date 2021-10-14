package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductQuantity;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

      int count = cart.getOrDefault(product, 0);

      cart.put(product, count + 1);


//        if (cart.containsKey(product)) {
//          int count = cart.get(product);
//          cart.put(product, count + 1);
//      } else {
//          cart.put(product, 1);
//      }

//        for(Product prod: cart.keySet()) {
//            if(product.equals(prod)){
//                int numberOfItem = cart.get(prod);
//                cart.replace(prod, numberOfItem + 1);
//            } else {
//                cart.put(product, 1);
//            }
//        }
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

    @Override
    public HashMap<String, String> getProductString() {
//        List<String> stringProductMap = new HashMap<>();
//        int counter = 1;
//        if(!cart.isEmpty()) {
//            for (Product prod : cart.keySet()) {
//                stringProductMap.put("prod" + counter, prod.toString());
//                counter++;
//            }
//
//        } else {
//            return null;
//        }
        return null;
    }

    @Override
    public HashMap<String, String> getQuantityProd(){
        HashMap<String, String> quantityProd = new HashMap<>();
        int counter = 1;
        if(!cart.isEmpty()) {
            for (int quantity:cart.values()){
                quantityProd.put(String.valueOf("quantity" + counter), String.valueOf(quantity));
            }
        } else {
            return null;
        }
        return quantityProd;
    }

    //        while (it.hasNext()) {
//            String key = it.next();
//            Object o = jObj.get(key);
//            System.out.println(key + " : " + o);
//        }
    public List<ProductQuantity> getQuantity() {
        List<ProductQuantity> prod = new ArrayList<>();
        for (Product product:cart.keySet()){
            prod.add(new ProductQuantity(product, cart.get(product)));
        }
        return prod;
    }

}
