package ro.shop.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.shop.model.OrderDetail;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class OrderDetailRepositoryTest {
    private OrderDetailRepository orderDetailRepository;
    private int idOrderDetail;
    @BeforeAll
    public static void pre(){

    }
    @BeforeEach
    public void initi(){
        orderDetailRepository = new OrderDetailRepository();
        orderDetailRepository.add(new OrderDetail(1,1,1,1));
        idOrderDetail = orderDetailRepository.lastID();
    }
    @AfterEach
    public void clean(){
        orderDetailRepository.delete(idOrderDetail);
    }
    @Test
    public void testAllOrderDetailList(){
        assertEquals(true,orderDetailRepository.allOrderDetailList().size()!=0);
    }
    @Test
    public void testUpdateOrderID(){
        orderDetailRepository.updateOrderID(idOrderDetail,5);
        assertEquals(5,orderDetailRepository.getOrderDetail(idOrderDetail).getOrderID());
    }
    @Test
    public void testUpdateProductID(){
        orderDetailRepository.updateProductID(idOrderDetail,5);
        assertEquals(5,orderDetailRepository.getOrderDetail(idOrderDetail).getProductID());
    }
    @Test
    public void testUpdatePrice(){
        orderDetailRepository.updatePrice(idOrderDetail,5);
        assertEquals(5,orderDetailRepository.getOrderDetail(idOrderDetail).getPrice());
    }
    @Test
    public void testUpdateQuantity(){
        orderDetailRepository.updateQuantity(idOrderDetail,5);
        assertEquals(5,orderDetailRepository.getOrderDetail(idOrderDetail).getQuantity());
    }


}