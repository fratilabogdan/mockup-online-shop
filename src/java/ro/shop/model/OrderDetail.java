package ro.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDetail implements Comparable<OrderDetail>{
    private int id;
    @EqualsAndHashCode.Exclude private int orderID;
    @EqualsAndHashCode.Exclude private int productID;
    @EqualsAndHashCode.Exclude private int price;
    @EqualsAndHashCode.Exclude private int quantity;

    public OrderDetail(int orderID, int productID, int price, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public int compareTo(OrderDetail orderDetail){
        if(orderDetail.id==this.id){
            return 0;
        }else if(orderDetail.id<this.id){
            return 1;
        }
        return -1;
    }
}
