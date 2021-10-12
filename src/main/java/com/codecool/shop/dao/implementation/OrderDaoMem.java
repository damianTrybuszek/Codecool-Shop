package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.user.Order;

public class OrderDaoMem implements OrderDao {

  private static OrderDaoMem instance = null;
  private Order data;

  private OrderDaoMem() {
  }

  public static OrderDaoMem getInstance() {
    if (instance == null) {
      instance = new OrderDaoMem();
    }
    return instance;
  }


  @Override
  public void set(Order order) {
    this.data = order;
  }

  @Override
  public Order get() {
    return data;
  }

  @Override
  public void remove() {
    this.data = null;
  }

  @Override
  public void edit() {
//    todo
  }

}
