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
    public void testDelete(){
        User validUser = new Admin("testdelete@test.com","TestPassword", "Test Name Delete", "Test address delete",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        userController.delete(id);
        assertEquals(null, userController.getUser(id));

    }
    @Test
    public void testGetAdmin(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        assertEquals("TestAdminPass", userController.getAdmin(id).getAdminPassword());
        userController.delete(id);
    }
    @Test
    public void testGetGuest(){
        User validUser = new Guest("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",9);
        userController.add(validUser);
        int id= userController.lastID();
        assertEquals(9, userController.getGuest(id).getTrialDays());
        userController.delete(id);
    }
    @Test
    public void testUpdateEmailAdmin(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        userController.updateEmail(id, "NewTestMail@test.com");
        assertEquals("NewTestMail@test.com", userController.getUser(id).getEmail());
        userController.delete(id);
    }
    @Test
    public void testUpdateEmailAdminFalse(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        userController.updateEmail(id, "NewTestMailtest.com");
        assertEquals(false, userController.getUser(id).getEmail().equals("NewTestMailtest.com"));
        userController.delete(id);
    }
    @Test
    public void testUpdateEmailAdminFalseDuplicate(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        userController.updateEmail(id, userController.getUser(idUser).getEmail());
        assertEquals(false, userController.getUser(id).getEmail().equals(userController.getUser(idUser).getEmail()));
        userController.delete(id);
    }
    @Test
    public void testUpdateFullName(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        userController.updateFullName(id, "Name Valid");
        assertEquals("Name Valid", userController.getUser(id).getFullName());
        userController.delete(id);
    }
    @Test
    public void testUpdateFullNameFalse(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        userController.updateFullName(id, "Name @Valid");
        assertEquals(false, userController.getUser(id).getFullName().equals("Name @Valid"));
        userController.delete(id);
    }
    @Test
    public void testUpdateAddress(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        userController.updateBillAddress(id, "Name Valid");
        assertEquals("Name Valid", userController.getUser(id).getBillAddress());
        userController.delete(id);
    }
    @Test
    public void testUpdateAddressFalse(){
        User validUser = new Admin("TestEmailValid@test.com","TestPassword", "Test Name Valid", "Test address valid",true,"TestAdminPass");
        userController.add(validUser);
        int id= userController.lastID();
        userController.updateBillAddress(id, userController.getUser(idUser).getBillAddress());
        assertEquals(false, userController.getUser(id).getBillAddress().equals(userController.getUser(idUser).getBillAddress()));
        userController.delete(id);
    }
    @Test
    public void testUpdateTrialDays(){
        User validUser = new Guest("guestmail@test.com","TestPassword", "Guest Name Valid", "Guest address valid",9);
        userController.add(validUser);
        int id= userController.lastID();
        System.out.println(userController.getUser(id));
        userController.updateTrialDays(id,10);
        assertEquals(10, userController.getGuest(id).getTrialDays());
        userController.delete(id);
    }
}