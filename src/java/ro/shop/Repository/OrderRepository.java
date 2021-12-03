package ro.shop.Repository;

import ro.shop.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class OrderRepository extends Repository{
    public ResultSet allObjects(){
        super.execute("select*from orders");
        try {
            return super.statement.getResultSet();
        }catch (Exception e){
            System.out.println("Orders resultset failed. Returning null");
            return null;
        }
    }
    public List<Order> allOrderList(){
        ResultSet set = allObjects();
        List<Order> orders = new ArrayList<>();
        try {
            while (set.next()){
                Order o = new Order(
                        set.getInt(1),
                        set.getInt(2),
                        set.getInt(3),
                        set.getString(4)
                );
                orders.add(o);
            }
        }catch (Exception e){
            System.out.println("Error on AllOrdersList");
            e.printStackTrace();
        }
        return orders;
    }
    public ResultSet byID(int id){
        String toID="select*from orders where id="+id;
        execute(toID);
        try {
            return statement.getResultSet();
        } catch (Exception e){
            System.out.println("ResultSet byID failed. Returning null");
            return null;
        }
    }
    public ResultSet resultSetLastID(){
        execute("select * from orders where id=(select max(id) from orders)");
        try {
            return statement.getResultSet();
        }catch (Exception e){
            System.out.println("resultSetLastID failed. Returning null");
            return null;
        }
    }
    public int lastID(){
        ResultSet set = resultSetLastID();
        List<Order> orders = new ArrayList<>();
        try {
            while (set.next()){
                Order o = new Order(
                        set.getInt(1),
                        set.getInt(2),
                        set.getInt(3),
                        set.getString(4)
                );
                orders.add(o);
            }
        }catch (Exception e){
            System.out.println("Error on AllOrdersList");
            e.printStackTrace();
        }
        return orders.get(0).getId();
    }
    public Order getOrder(int id){
            ResultSet set = byID(id);
            List<Order> orders = new ArrayList<>();
            try {
                while (set.next()){
                    Order o = new Order(
                            set.getInt(1),
                            set.getInt(2),
                            set.getInt(3),
                            set.getString(4)
                    );
                    orders.add(o);
                }
            }catch (Exception e){
                System.out.println("Error on AllOrdersList");
                e.printStackTrace();
            }
            return orders.get(0);
    }

    public boolean add(Order o){
        String insertTo="insert into orders (userid, amount, orderstatus) VALUES ";
        insertTo+=String.format(("(%d,%d,'%s');"),
                o.getUserID(),o.getAmount(),o.getOrderStatus());
        return execute(insertTo);
    }
    public boolean delete(int id){
        String del="delete orders from orders where id="+id;
        return execute(del);
    }
    public boolean updateUserID(int id, int newUserID){
        String update = "update orders set userid="+newUserID+" where id="+id;
        return execute(update);
    }
    public boolean updateAmount(int id, int newAmount){
        String update = "update orders set amount="+newAmount+" where id="+id;
        return execute(update);
    }
    public boolean updateOrderStatus(int id, String newStatus){
        String update = "update orders set orderstatus='"+newStatus+"' where id="+id;
        return execute(update);
    }
}
