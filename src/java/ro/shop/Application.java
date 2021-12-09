package ro.shop;

import ro.shop.Repository.OrderRepository;
import ro.shop.Repository.UserRepository;
import ro.shop.model.Order;
import ro.shop.model.OrderDetail;
import ro.shop.model.Product;
import ro.shop.model.product.ElectronicProduct;
import ro.shop.model.user.Admin;
import ro.shop.model.user.Guest;
import ro.shop.view.UserView;

public class Application {

    public static void main(String[] args) {
        UserView userView = new UserView();

        userView.play();
    }
}
