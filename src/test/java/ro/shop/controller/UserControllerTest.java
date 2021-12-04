package ro.shop.controller;

import org.junit.jupiter.api.*;
import ro.shop.Repository.OrderRepository;
import ro.shop.Repository.UserRepository;
import ro.shop.model.Order;
import ro.shop.model.User;
import ro.shop.model.user.Admin;
import ro.shop.model.user.Guest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;



class UserControllerTest {
    private UserController userController;
    private int idUser;

    @BeforeAll
    public static void pre(){

    }
    @BeforeEach
    public void initi(){
        userController = new UserController();
        User validUser = new Admin("TestEmail@test.com","TestPassword", "Test Name", "Test address",true,"TestAdminPass");
//        User invalidUser = new User("TestEmail@test.com","TestPassword", "Test Name", "Test address");
        userController.add(validUser);
        idUser= userController.lastID();
    }
    @AfterEach
    public void clean(){
        userController.delete(idUser);

    }
    @AfterAll
    public static void cleanAll(){
        UserController userController = new UserController();
        userController.removeDuplicates();
    }


    @Test
    public void testValidMail(){
        assertEquals(true, userController.validMail(userController.getUser(idUser).getEmail()));
    }
    @Test
    public void testInValidMail(){
        User invalidUser = new Guest("TestEmailtestcom","TestPassword", "Test Name", "Test address",5);
        assertEquals(false, userController.validMail(invalidUser.getEmail()));
    }
    @Test
    public void testValidName(){
        assertEquals(true, userController.validName(userController.getUser(idUser).getFullName()));
    }
    @Test
    public void testInValidName(){
        User invalidUser = new Guest("TestEmail@test.com","TestPassword", "Test1 Name", "Test address",5);
        assertEquals(false, userController.validMail(invalidUser.getFullName()));
    }
    @Test
    public void testInValidUserForName(){
        User invalidUser = new Guest("TestEmail@test.com","TestPassword", "Test1 Name", "Test address",5);
        assertEquals(false, userController.validUser(invalidUser));
    }
    @Test
    public void testInValidUserForEmail(){
        User invalidUser = new Guest("TestEmailtest.com","TestPassword", "Test Name", "Test address",5);
        assertEquals(false, userController.validUser(invalidUser));
    }
    @Test
    public void testValidUser(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        assertEquals(true, userController.validUser(validUser));
    }
    @Test
    public void testDuplicateFalse(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        assertEquals(false, userController.duplicate(validUser));
    }
    @Test
    public void testDuplicateTrue(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        assertEquals(true, userController.duplicate(validUser));
        userController.delete(id);
    }
    @Test
    public void testRemoveDuplicates(){
        userController.removeDuplicates();
    }
}