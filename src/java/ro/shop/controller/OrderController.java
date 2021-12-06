package ro.shop.controller;

import ro.shop.Repository.OrderRepository;
import ro.shop.model.Order;

import java.util.List;

public class OrderController {
    OrderRepository orderRepository;
    List<Order> orderList;

    public OrderController(){
        orderRepository = new OrderRepository();
        this.orderRepository = orderRepository;
        this.orderList = orderRepository.allOrderList();
    }

    public int lastID(){
        return orderRepository.lastID();
    }

}
