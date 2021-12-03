package ro.shop.Repository;

import org.junit.jupiter.api.*;
import ro.shop.model.User;
import ro.shop.model.user.Admin;
import ro.shop.model.user.Guest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

   private UserRepository userRepository;
   private int idAdmin;
   private int idGuest;
   private List<User> list;

    @BeforeAll
    public static void pre(){
    }
    @BeforeEach
    public void initi(){
        userRepository = new UserRepository();

        userRepository.add(new Admin("Testmail@test.com","TestFirstPass","Test Admin name", "Test bill address",true,"TestAdminPass"));
        idAdmin= userRepository.lastID();

        userRepository.add(new Guest("Testmail@test.com","TestFirstPass","Test Guest name", "Test bill address",5));
        idGuest= userRepository.lastID();
    }

    @AfterEach
    public  void post(){

            userRepository.delete(idAdmin);
            userRepository.delete(idGuest);
    }
    @AfterAll
    public static void clean(){
    }

    @Test
    public void testAllUserList(){
        assertEquals(true,userRepository.allUserList().size()!=0);
    }
    @Test
    public void testAdd(){
        assertEquals("Test Guest name", userRepository.getUser(idGuest).getFullName());
    }
    @Test
    public void testUpdateMail(){
        userRepository.updateEmail(idGuest,"T1");
        assertEquals("T1", userRepository.getUser(idGuest).getEmail());
    }
    @Test
    public void testUpdateName(){
        userRepository.updateFullName(idGuest,"T1");
        assertEquals("T1", userRepository.getUser(idGuest).getFullName());
    }
    @Test
    public void testUpdateAddress(){
        userRepository.updateBillAddress(idGuest,"T1");
        assertEquals("T1", userRepository.getUser(idGuest).getBillAddress());
    }
    @Test
    public void testUpdateAdminAccess(){
        userRepository.updateAdminAccess(idAdmin,false);
        assertEquals(false, ((Admin) userRepository.getUser(idAdmin)).isAdminAccess());
    }
    @Test
    public void testUpdateAdminAccessTrue(){
        userRepository.updateAdminAccess(idAdmin,true);

        assertEquals(true, ((Admin) userRepository.getUser(idAdmin)).isAdminAccess());

    }
    @Test
    public void testUpdateAdminPass(){
        userRepository.updateAdminPassword(idAdmin,"T1");
        assertEquals("T1", ((Admin) userRepository.getUser(idAdmin)).getAdminPassword());
    }
    @Test
    public void testUpdatePass(){
        userRepository.updatePassword(idAdmin,"T1");


        assertEquals("T1", ((Admin) userRepository.getUser(idAdmin)).getPassword());
    }
    @Test
    public void testUpdateTrialDays(){
        userRepository.updateTrialDays(idGuest,1);
        assertEquals(1, ((Guest) userRepository.getUser(idGuest)).getTrialDays());
    }

}