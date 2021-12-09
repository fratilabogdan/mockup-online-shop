package ro.shop.controller;

import ro.shop.Repository.OrderDetailRepository;
import ro.shop.model.OrderDetail;

import java.util.Iterator;
import java.util.List;

public class OrderDetailController {
    OrderDetailRepository orderDetailRepository;
    List<OrderDetail> orderDetailList;

    public OrderDetailController(){
        orderDetailRepository = new OrderDetailRepository();
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailList = orderDetailRepository.allOrderDetailList();
    }

    public boolean add(OrderDetail order){
        if(ifValid(order) && !duplicate(order)){
            return orderDetailRepository.add(order);
        }
        return false;
    }
    public boolean delete(int id){
        if(containsID(id)){
            return orderDetailRepository.delete(id);
        }
        return false;
    }
    public boolean updateOrderID(int id, int newId){
        OrderDetail order = getOrderDetail(id);
        order.setOrderID(newId);
        if(ifValid(order) && !duplicate(order)){
            return orderDetailRepository.updateOrderID(id,newId);
        }
        return false;
    }
    public boolean updateProductID(int id, int newId){
        OrderDetail order = getOrderDetail(id);
        order.setProductID(newId);
        if(ifValid(order) && !duplicate(order)){
            return orderDetailRepository.updateProductID(id,newId);
        }
        return false;
    }
    public boolean updateQuantity(int id, int newQuantity){
        OrderDetail order = getOrderDetail(id);
        order.setQuantity(newQuantity);
        if(ifValid(order) && !duplicate(order)){
            return orderDetailRepository.updateQuantity(id, newQuantity);
        }
        return false;
    }


    //Problem
    //:todo solve
//    public boolean updatePrice(int id, int newPrice){
//        return orderDetailRepository.updatePrice(newPrice);
//    }


    public OrderDetail getOrderDetail(int id){
        if(containsID(id)){
            return orderDetailRepository.getOrderDetail(id);
        }
        return null;
    }

    public int lastID(){
        return orderDetailRepository.lastID();
    }
    public boolean containsID(int id){
        Iterator<OrderDetail> it = orderDetailRepository.allOrderDetailList().iterator();
        while (it.hasNext()){
            OrderDetail u=it.next();
            if(u.getId()==id){
                return true;
            }
        }
        return false;
    }

    public int nbOfDuplicates(OrderDetail order){
        int count=1;
        Iterator<OrderDetail> it = orderDetailRepository.allOrderDetailList().iterator();
        while (it.hasNext()){
            OrderDetail u=it.next();
            if(order.getId()==u.getId()){
                count++;
            }
        }
        return count;
    }
    public boolean duplicate(OrderDetail order){
        int count=1;
        Iterator<OrderDetail> it = orderDetailRepository.allOrderDetailList().iterator();
        while (it.hasNext()){
            OrderDetail u=it.next();
            if(order.getId()==u.getId()){
                count++;
            }
        }
        if(count<2){
            return false;
        }
        return true;
    }

    public boolean ifValid(OrderDetail order){
        OrderController orderController = new OrderController();
        ProductController productController = new ProductController();
        if (
                orderController.containsID(order.getOrderID()) &&
                        orderController.getOrder(order.getOrderID()).getAmount()<=order.getQuantity() &&
                        productController.containsID(order.getProductID())
        ) {
            return true;
        }
        return false;
    }
    public boolean removeDuplicatesInvalids(){
        int count=0;
        Iterator<OrderDetail> it = orderDetailRepository.allOrderDetailList().iterator();
        while (it.hasNext()){
            OrderDetail b = it.next();
            if(ifValid(b)==false){
                orderDetailRepository.delete(b.getId());
            } else {
                if (nbOfDuplicates(b) >= 3) {
                    count++;
                    orderDetailRepository.delete(b.getId());
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
