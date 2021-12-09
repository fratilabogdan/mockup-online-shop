package ro.shop.view;
import ro.shop.controller.OrderController;
import ro.shop.controller.OrderDetailController;
import ro.shop.controller.ProductController;
import ro.shop.model.Order;
import ro.shop.model.OrderDetail;
import ro.shop.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;


public class UserView {
    private OrderController orderController;
    private OrderDetailController orderDetailController;
    private ProductController productController;
    private Scanner scanner;
    private OrderDetail orderDetail;
    private Order order;
    private int userID;
    private List<OrderDetail> shoppingCart;

    public UserView(){
        this.orderController = new OrderController();
        this.orderDetailController = new OrderDetailController();
        this.productController = new ProductController();
        this.scanner = new Scanner(System.in);
        this.shoppingCart = new ArrayList<>();
        this.userID = 1;
        this.order = new Order(userID,0,"In cart");
        this.orderDetail = new OrderDetail(1,order.getId(),1,1,1);

        //cream un order
        //persoana  logata
    }


    //1)afisare produse
    //2))add c0s
    //3) view cos
    //4) edit cos
    //5)erase product
    //6)trimite comanda
    public void menu(){
        System.out.println("");
        System.out.println("Press "+1+" to see all available products");
        System.out.println("Press "+2+" to add a product to the cart");
        System.out.println("Press "+3+" to view all products in the cart");
        System.out.println("Press "+4+" to edit a product's quantity in the cart");
        System.out.println("Press "+5+" to remove a product from the cart");
        System.out.println("Press "+6+" to buy the items in the cart");
    }





    public void play(){
        boolean run=true;

        while (run==true){
            menu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:showAllProducts();
                break;
                case 2:addProductToCart();
                break;
                case 3:viewAllProductsInCart();
                break;
                case 4:changeQuantity();
                break;
            }
        }
    }

    public void showAllProducts(){
        productController.showAllProducts();
    }
    public void addProductToCart(){
        System.out.println("What product do you wish to purchase?");
        String productName = scanner.nextLine();
        int quantity = 0;
        if(productController.getProductByName(productName)==null){
            System.out.println("Product name invalid");
        } else {

            Product product = productController.getProductByName(productName);
            System.out.println("Select quantity");
            quantity = Integer.parseInt(scanner.nextLine());
            int price = product.getPrice();

            OrderDetail shopping = new OrderDetail(order.getId(),product.getId(),price,quantity);
            shoppingCart.add(shopping);
            System.out.println(quantity+"x "+productName+" successfully added in shopping cart");
            order.setAmount(shoppingCart.size());
            System.out.println(shopping);
            System.out.println(order);
        }
    }
    public void viewAllProductsInCart(){
        Iterator<OrderDetail> it = shoppingCart.iterator();
        while (it.hasNext()){
            System.out.println(productController.getProduct(it.next().getProductID()).getName());
        }
    }
    public Product getProductFromShoppingCart(String productName){
        Product product = productController.getProductByName(productName);
        if(product==null){
            System.out.println("Product name invalid");
        } else {
            if(!shoppingCart.contains(product)){
                System.out.println("Product is not in shopping cart");
            } else {
                Iterator<OrderDetail> it = shoppingCart.iterator();
                while (it.hasNext()){
                    OrderDetail orderDetail = it.next();
                    String pName = productController.getProduct(orderDetail.getProductID()).getName();
                    if(productName.equals(pName)){
                        return productController.getProduct(orderDetail.getProductID());
                    }
                }
            }

        }
        return null;
    }

    public void changeQuantity(){
        System.out.println("Type the product");
        String productName = scanner.nextLine();
        if(getProductFromShoppingCart(productName)==null){
            System.out.println("Product name invalid or not in shopping cart");
        }else {
            System.out.println("Select new quantity");
            int quantity = Integer.parseInt(scanner.nextLine());

            Iterator<OrderDetail> it = shoppingCart.iterator();
            while (it.hasNext()){
                OrderDetail orderDetail = it.next();
                Product product = getProductFromShoppingCart(productName);
                if(orderDetail.getProductID()==product.getId()){
                    orderDetail.setQuantity(quantity);
                    orderDetail.setPrice(quantity*product.getPrice());
                    System.out.println(productName + " new quantity is "+ quantity + " and price was updated to "+ orderDetail.getPrice());
                }
            }
        }
    }





}
