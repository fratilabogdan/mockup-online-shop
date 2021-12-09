package ro.shop.controller;

import ro.shop.Repository.OrderRepository;
import ro.shop.model.Order;
import ro.shop.model.User;

import java.util.Iterator;
import java.util.List;

public class OrderController {
    OrderRepository orderRepository;
    List<Order> orderList;

    public OrderController(){
        orderRepository = new OrderRepository();
        this.orderRepository = orderRepository;
        this.orderList = orderRepository.allOrderList();
    }

    public boolean add(Order order){
        if(ifValid(order) && !duplicate(order)){
            return orderRepository.add(order);
        }
        return false;
    }
    public boolean delete(int id){
        if(containsID(id)){
            return orderRepository.delete(id);
        }
        return false;
    }
    public boolean updateUserID(int id, int newId){
        Order order = getOrder(id);
        order.setUserID(newId);
        if(ifValid(order) && !duplicate(order)){
            return orderRepository.updateUserID(id,newId);
        }
        return false;
    }
    public boolean updateAmount(int id, int newAmount){
        if(newAmount>=0) {
            Order order = getOrder(id);
            order.setAmount(newAmount);
            if (ifValid(order) && !duplicate(order)) {
                return orderRepository.updateAmount(id, newAmount);
            }
        }
        return false;
    }


    //:todo
    //problem
//    public boolean updateStatus(int id, String newStatus){
//        Order order = getOrder(id);
//        if(order.getOrderStatus().equals(newStatus)){
//            return false;
//        }else {
//            return orderRepository.updateOrderStatus(newStatus);
//        }
//    }


    public Order getOrder(int id){
        if(containsID(id)){
            return orderRepository.getOrder(id);
        }
        return null;
    }

    public int lastID(){
        return orderRepository.lastID();
    }
    public boolean containsID(int id){
        Iterator<Order> it = orderRepository.allOrderList().iterator();
        while (it.hasNext()){
            Order u=it.next();
            if(u.getId()==id){
                return true;
            }
        }
        return false;
    }
    public int nbOfDuplicates(Order order){
        int count=1;
        Iterator<Order> it = orderRepository.allOrderList().iterator();
        while (it.hasNext()){
            Order u=it.next();
            if(order.getId()==u.getId()){
                count++;
            }
        }
        return count;
    }
    public boolean duplicate(Order order){
        int count=1;
        Iterator<Order> it = orderRepository.allOrderList().iterator();
        while (it.hasNext()){
            Order u=it.next();
            if(order.getId()==u.getId()){
                count++;
            }
        }
        if(count<2){
            return false;
        }
        return true;
    }

    public boolean ifValid(Order order){
        UserController userController = new UserController();
        if (userController.containsID(order.getUserID())) {
            return true;
        }
        return false;
    }
    public boolean removeDuplicatesInvalids(){
        int count=0;
        Iterator<Order> it = orderRepository.allOrderList().iterator();
        while (it.hasNext()){
            Order b = it.next();
            if(ifValid(b)==false){
                orderRepository.delete(b.getId());
            } else {
                if (nbOfDuplicates(b) >= 3) {
                    count++;
                    orderRepository.delete(b.getId());
                }
            }
        }
        if(count>=1){
            System.out.println("Total duplicates removed: "+count);
            return true;
        }
        return false;
    }

}
