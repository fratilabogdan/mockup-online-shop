package ro.shop.Repository;

import ro.shop.model.Product;
import ro.shop.model.product.ElectronicProduct;
import ro.shop.model.product.WearableProduct;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Repository{


    public ResultSet allObjects(){
        super.execute("select*from product");
        try {
            return super.statement.getResultSet();
        }catch (Exception e){
            System.out.println("Product resultset failed. Returning null");
            return null;
        }
    }
    public List<Product> allProducts(){
        ResultSet set = allObjects();
        List<Product> products = new ArrayList<>();
        try {
            while (set.next()){
                String  text=set.getString(4);
                if(text.equals("Wearable")){
                    Product p = new WearableProduct(
                            set.getInt(1),
                            set.getString(2),
                            set.getInt(3),
                            set.getInt(5),
                            set.getString(7),
                            set.getString(8)
                            );
                    products.add(p);
                } else if(text.equals("Electronics")){
                    Product p = new ElectronicProduct(
                            set.getInt(1),
                            set.getString(2),
                            set.getInt(3),
                            set.getInt(5),
                            set.getBoolean(6)
                    );
                    products.add(p);
                }
            }
        }catch ( Exception e){
            System.out.println("Error on allProducts");
            e.printStackTrace();
        }
        return products;
    }
    public ResultSet byID(int id){
        String toID="select*from product where id="+id;
        execute(toID);
        try {
            return statement.getResultSet();
        } catch (Exception e){
            System.out.println("ResultSet byID failed. Returning null");
            return null;
        }
    }
    public ResultSet resultSetLastID(){
        execute("select * from product where id=(select max(id) from product)");
        try {
            return statement.getResultSet();
        }catch (Exception e){
            System.out.println("resultSetLastID failed. Returning null");
            return null;
        }
    }

    public Product getProduct(int id) {
        ResultSet set = byID(id);
        List<Product> products = new ArrayList<>();
        try {
            while (set.next()) {
                String text = set.getString(4);
                if (text.equals("Wearable")) {
                    Product p = new WearableProduct(
                            set.getInt(1),
                            set.getString(2),
                            set.getInt(3),
                            set.getInt(5),
                            set.getString(7),
                            set.getString(8)
                    );
                    products.add(p);
                } else if (text.equals("Electronics")) {
                    Product p = new ElectronicProduct(
                            set.getInt(1),
                            set.getString(2),
                            set.getInt(3),
                            set.getInt(5),
                            set.getBoolean(6)
                    );
                    products.add(p);
                }
            }
        }catch (Exception e){
            System.out.println("Error on getProduct");
        }
        return products.get(0);
    }
    public int lastID(){
        ResultSet set = resultSetLastID();
        List<Product> products = new ArrayList<>();
        try {
            while (set.next()) {
                String text = set.getString(4);
                if (text.equals("Wearable")) {
                    Product p = new WearableProduct(
                            set.getInt(1),
                            set.getString(2),
                            set.getInt(3),
                            set.getInt(5),
                            set.getString(7),
                            set.getString(8)
                    );
                    products.add(p);
                } else if (text.equals("Electronics")) {
                    Product p = new ElectronicProduct(
                            set.getInt(1),
                            set.getString(2),
                            set.getInt(3),
                            set.getInt(5),
                            set.getBoolean(6)
                    );
                    products.add(p);
                }
            }
        }catch (Exception e){
            System.out.println("Error on lastID");
        }
        return products.get(0).getId();
    }

    public boolean add(Product p){

        if(p instanceof WearableProduct) {

            WearableProduct a=(WearableProduct)p;
            String insertTO = "insert into product (name, price, category, stock, wearabletype, material) values";
            insertTO+=String.format(("('%s',%d,'%s',%d,'%s','%s');"),
                    a.getName(),a.getPrice(),a.getCategory(),a.getStock(),a.getWearableType(),a.getMaterial());
            return execute(insertTO);
        } else if(p instanceof ElectronicProduct){
            ElectronicProduct e=(ElectronicProduct) p;
            String insertTO = "insert into product (name, price, category, stock, isdangerous) values";

            int booleanVal=0;
            if(e.isDangerous()){
                booleanVal=1;
            }
            insertTO+=String.format(("('%s',%d,'%s',%d,%d);"),
                    e.getName(),e.getPrice(),e.getCategory(),e.getStock(),booleanVal);
            return execute(insertTO);
        }
        return false;
    }
    public boolean updateName(int id, String newName){
        String update="update product set name='"+newName+"' where id="+id;
        return execute(update);
    }
    public boolean updatePrice(int id, int newPrice){
        String update="update product set price="+ newPrice +" where id="+id;
        return execute(update);
    }

    public boolean updateStock(int id, int newStock){
        String update="update product set stock="+ newStock +" where id="+id;
        return execute(update);
    }
    public boolean updateIsDangerous(int id, boolean newDangerous){
        if(!getProduct(id).getCategory().equals("Electronics")){
            return false;
        }
        int val=0;
        if(newDangerous){
            val=1;
        }
        String update="update product set isdangerous="+ val +" where id="+id;
        return execute(update);
    }
    public boolean updateWearableType(int id, String newType){
        if(!getProduct(id).getCategory().equals("Wearable")){
            return false;
        }
        String update="update product set wearabletype='"+ newType +"' where id="+id;
        return execute(update);
    }
    public boolean updateMaterial(int id, String newMat){
        if(!getProduct(id).getCategory().equals("Wearable")){
            return false;
        }
        String update="update product set material='"+ newMat +"' where id="+id;
        return execute(update);
    }
    public boolean delete(int id){
        String del="delete product from product where id="+id;
        return execute(del);
    }


}
