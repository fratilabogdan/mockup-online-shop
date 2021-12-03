package ro.shop.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.shop.model.Order;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private OrderRepository orderRepository;
    private int idOrder;

    @BeforeAll
    public static void pre(){

    }
    @BeforeEach
    public void initi(){
        orderRepository = new OrderRepository();
        orderRepository.add(new Order(1,1,"TestStatus"));
        idOrder=orderRepository.lastID();
    }
    @AfterEach
    public void clean(){
        orderRepository.delete(idOrder);
    }

    @Test
    public void testAllOrderList(){
        assertEquals(true,orderRepository.allOrderList().size()!=0);
    }
    @Test
    public void testUpdateUserID(){
        orderRepository.updateUserID(idOrder, 5);
        assertEquals(5,orderRepository.getOrder(idOrder).getUserID());
    }
    @Test
    public void testUpdateAmount(){
        orderRepository.updateAmount(idOrder, 5);
        assertEquals(5,orderRepository.getOrder(idOrder).getAmount());
    }
    @Test
    public void testUpdateOrderStatus(){
        orderRepository.updateOrderStatus(idOrder, "NewStatus");
        assertEquals("NewStatus",orderRepository.getOrder(idOrder).getOrderStatus());
    }

}