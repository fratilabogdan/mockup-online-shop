package ro.shop.Repository;

import ro.shop.model.OrderDetail;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepository extends Repository{
    public ResultSet allObjects(){
        super.execute("select*from order_details");
        try {
            return super.statement.getResultSet();
        }catch (Exception e){
            System.out.println("User resultset failed. Returning null");
            return null;
        }
    }
    public List<OrderDetail> allOrderDetailList(){
        ResultSet set = allObjects();
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            while (set.next()){
                OrderDetail orderDetail = new OrderDetail(
                        set.getInt(1),
                        set.getInt(2),
                        set.getInt(3),
                        set.getInt(4),
                        set.getInt(5)
                );
                orderDetails.add(orderDetail);
            }

        }catch (Exception e){
            System.out.println("Error on allOrderDetailList");
            e.printStackTrace();
        }
        return orderDetails;
    }
    public ResultSet byID(int id){
        String toID="select*from order_details where id="+id;
        execute(toID);
        try {
            return statement.getResultSet();
        } catch (Exception e){
            System.out.println("ResultSet byID failed. Returning null");
            return null;
        }
    }
    public ResultSet resultSetLastID(){
        execute("select * from order_details where id=(select max(id) from order_details)");
        try {
            return statement.getResultSet();
        }catch (Exception e){
            System.out.println("resultSetLastID failed. Returning null");
            return null;
        }
    }
    public int lastID(){
        ResultSet set = resultSetLastID();
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            while (set.next()){
                OrderDetail orderDetail = new OrderDetail(
                        set.getInt(1),
                        set.getInt(2),
                        set.getInt(3),
                        set.getInt(4),
                        set.getInt(5)
                );
                orderDetails.add(orderDetail);
            }

        }catch (Exception e){
            System.out.println("Error on lastID");
            e.printStackTrace();
        }
        return orderDetails.get(0).getId();
    }
    public OrderDetail getOrderDetail(int id){
        ResultSet set = byID(id);
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            while (set.next()){
                OrderDetail orderDetail = new OrderDetail(
                        set.getInt(1),
                        set.getInt(2),
                        set.getInt(3),
                        set.getInt(4),
                        set.getInt(5)
                );
                orderDetails.add(orderDetail);
            }

        }catch (Exception e){
            System.out.println("Error on lastID");
            e.printStackTrace();
        }
        return orderDetails.get(0);
    }

    public boolean add(OrderDetail o){
        String insertTo="insert into order_details (orderid, productid, price, quantity) VALUES ";
        insertTo+=String.format(("(%d,%d,%d,%d);"),
        o.getOrderID(),o.getProductID(),o.getPrice(),o.getQuantity());
        return execute(insertTo);
    }
    public boolean delete(int id){
        String del="delete order_details from order_details where id="+id;
        return execute(del);
    }
    public boolean updateOrderID(int id, int newID){
        String update = "update order_details set orderid="+newID+" where id="+id;
        return execute(update);
    }
    public boolean updateProductID(int id, int newID){
        String update = "update order_details set productid="+newID+" where id="+id;
        return execute(update);
    }
    public boolean updatePrice(int id, int newPrice){
        String update = "update order_details set price="+newPrice+" where id="+id;
        return execute(update);
    }
    public boolean updateQuantity(int id, int newQuantity){
        String update = "update order_details set quantity="+newQuantity+" where id="+id;
        return execute(update);
    }

}
