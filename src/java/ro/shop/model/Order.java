package ro.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order implements Comparable<Order>{
    private int id;
    @EqualsAndHashCode.Exclude private int userID;
    @EqualsAndHashCode.Exclude private int amount;
    @EqualsAndHashCode.Exclude private String orderStatus;

    public Order(int userID, int amount, String orderStatus) {
        this.userID = userID;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }

    @Override
    public int compareTo(Order order){
        if(order.id==this.id){
            return 0;
        }else if(order.id<this.id){
            return 1;
        }
        return -1;
    }

}
