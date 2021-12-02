package ro.shop.Repository;

import ro.shop.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository{
    public ResultSet allUsers(){
        super.execute("select*from users");
        try {
            return super.statement.getResultSet();
        }catch (Exception e){
            System.out.println("User resultset failed. Returning null");
            return null;
        }
    }
//    public List<User> allUserList(){
//        ResultSet set=allUsers();
//        List<User> users = new ArrayList<>();
//        try {
//            while (set.next()){
//                String text=set.getString(7);
//                if(text.contains("null")){
//
//                }
//            }
//        }
//    }

}
