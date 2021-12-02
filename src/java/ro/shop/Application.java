package ro.shop;

import ro.shop.model.OrderDetail;
import ro.shop.model.Product;
import ro.shop.model.product.ElectronicProduct;

public class Application {

    public static void main(String[] args) {

        Product product = new Product(1,"Name",50,"ClasT",1);
        OrderDetail orderDetail = new OrderDetail(1,1,1,1,1);
        ElectronicProduct electronicProduct = new ElectronicProduct(1,"Class",1,1,true);
        System.out.println(electronicProduct.toString());
    }
}
