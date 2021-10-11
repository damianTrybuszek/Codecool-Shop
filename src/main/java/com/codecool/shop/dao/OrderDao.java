package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.user.Order;

public interface OrderDao {

  void set(Order order);

  Order get();

  void remove();

  void edit();
}
