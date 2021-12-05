package ro.shop.Repository;

import ro.shop.model.User;
import ro.shop.model.user.Admin;
import ro.shop.model.user.Guest;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository{
    public ResultSet allObjects(){
        super.execute("select*from users");
        try {
            return super.statement.getResultSet();
        }catch (Exception e){
            System.out.println("User resultset failed. Returning null");
            return null;
        }
    }
    public List<User> allUserList(){
        ResultSet set= allObjects();
        List<User> users = new ArrayList<>();
        try {
            while (set.next()){
                String text=set.getString(7);

                if(text!=null){
                    User u=new Admin(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getString(5),
                            set.getBoolean(6),
                            set.getString(7)
                    );
                    users.add(u);
                } else {
                    User u=new Guest(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getString(5),
                            set.getInt(8)
                    );
                    users.add(u);
                }
            }
        } catch (Exception e){
            System.out.println("Error on allUserList");
            e.printStackTrace();
        }
        return users;
    }
    public ResultSet byID(int id){
        String toID="select*from users where id="+id;
        execute(toID);
        try {
            return statement.getResultSet();
        } catch (Exception e){
            System.out.println("ResultSet byID failed. Returning null");
            return null;
        }
    }
    public ResultSet resultSetLastID(){
        execute("select * from users where id=(select max(id) from users)");
        try {
            return statement.getResultSet();
        }catch (Exception e){
            System.out.println("resultSetLastID failed. Returning null");
            return null;
        }
    }
    public int lastID(){
        ResultSet set=resultSetLastID();
        List<User> users = new ArrayList<>();
        try {
            while (set.next()){
                String text=set.getString(7);

                if(text!=null){
                    User u=new Admin(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getString(5),
                            set.getBoolean(6),
                                set.getString(7)
                        );
                        users.add(u);
                    } else {
                        User u=new Guest(
                                set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getString(5),
                            set.getInt(8)
                    );
                    users.add(u);
                }
            }
        } catch (Exception e){
            System.out.println("Error on lastID");
            e.printStackTrace();
        }
        return users.get(0).getId();
    }
    public User getUser(int id){
        ResultSet set = byID(id);
        List<User> users = new ArrayList<>();
        try {
            while (set.next()){

                String text=set.getString(7);

                if(text!=null){

                    User u=new Admin(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getString(5),
                            set.getBoolean(6),
                            set.getString(7)
                    );
                    users.add(u);

                } else {
                    User u=new Guest(
                            set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getString(5),
                            set.getInt(8)
                    );

                    users.add(u);
                }
            }
        } catch (Exception e){
            System.out.println("Error on getUser");
            e.printStackTrace();
        }
        return users.get(0);
    }

    public boolean add(User u){
        if(u instanceof Admin){
            Admin a=(Admin) u;
            int booleanVal=0;
            if(a.isAdminAccess()){
                booleanVal=1;
            }
            String insertTo = "insert into users (email, password, fullname, billaddress, adminaccess, adminpassword) VALUES ";
            insertTo+=String.format(("('%s','%s','%s','%s',%d,'%s');"),
            a.getEmail(),a.getPassword(),a.getFullName(),a.getBillAddress(),booleanVal,a.getAdminPassword());
            return execute(insertTo);
        } else {
            Guest g = (Guest) u;
            String insertTo = "insert into users (email, password, fullname, billaddress, trialdays) VALUES ";
            insertTo+=String.format(("('%s','%s','%s','%s',%d);"),
                    g.getEmail(),g.getPassword(),g.getFullName(),g.getBillAddress(),g.getTrialDays());
            return execute(insertTo);
        }

    }
    public boolean delete(int id){
        String del="delete users from users where id="+id;
        return execute(del);
    }
    public boolean updateEmail(int id, String newMail){
        String update="update users set email='"+newMail+"' where id="+id;
        return execute(update);
    }
    public boolean updateFullName(int id, String newName){
        String update="update users set fullname='"+newName+"' where id="+id;
        return execute(update);
    }
    public boolean updateBillAddress(int id, String newAddress){
        String update="update users set billaddress='"+newAddress+"' where id="+id;
        return execute(update);
    }
    public boolean updateTrialDays(int id, int newDays){
        if(isAdmin(id)){
            return false;
        }
        String update="update users set trialdays="+newDays+" where id="+id;
        return execute(update);
    }
    public boolean updateAdminAccess(int id, boolean newAccess){
        if(!isAdmin(id)){
            return false;
        }
        int val=0;
        if(newAccess){
            val=1;
        }
        String update="update users set adminaccess="+ val +" where id="+id;
        return execute(update);
    }
    public boolean isAdmin(int id){
        ResultSet set = byID(id);
        List<User> users = new ArrayList<>();
        try {
            while (set.next()){
                String text=set.getString(7);

                if(text!=null){
                    return true;
                }
            }
        } catch (Exception e){
            System.out.println("Error on getUser");
            e.printStackTrace();
        }
        return false;
    }
    public boolean updatePassword(int id, String newPassword){
        String update="update users set password='"+newPassword+"' where id="+id;
        return execute(update);
    }
    public boolean updateAdminPassword(int id, String newPassword){
        String update="update users set adminpassword='"+newPassword+"' where id="+id;
        return execute(update);
    }
}
