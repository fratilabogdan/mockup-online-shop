package ro.shop.controller;

import jdk.swing.interop.SwingInterOpUtils;
import ro.shop.Repository.UserRepository;
import ro.shop.model.User;
import ro.shop.model.user.Guest;
import ro.shop.model.user.Admin;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserController {
    UserRepository userRepository;
    List<User> userList;

    public UserController(){
        userRepository = new UserRepository();
        this.userRepository = userRepository;
        this.userList = userRepository.allUserList();
    }

    //CRUD
    public boolean add(User user){
        if(validUser(user)){
            return userRepository.add(user);
        }
        return false;
    }
    public boolean delete(int id){
        if(containsID(id)){
            return userRepository.delete(id);
        }
        return false;
    }
    public boolean updateEmail(int id, String newEmail){
        User user = getUser(id);
        user.setEmail(newEmail);
        if(validMail(newEmail)){
            return userRepository.updateEmail(id,newEmail);
        }
        return false;
    }
    public boolean updateFullName(int id, String newName){
        User user = getUser(id);
        user.setFullName(newName);
        if(validName(newName)){
            return userRepository.updateFullName(id,newName);
        }
        return false;
    }
    public boolean updateBillAddress(int id, String newAddress){
        int count=1;
        Iterator<User> it = userRepository.allUserList().iterator();
        while (it.hasNext()){
            User u=it.next();
            if(newAddress.equals(u.getBillAddress())){
                count++;
            }
        }
        if(count>=2){
            return false;
        } else {
            return userRepository.updateBillAddress(id,newAddress);
        }
    }
    public boolean updateTrialDays(int id, int newDays){
        if((getUser(id) instanceof Admin)){
            return false;
        }

            return userRepository.updateTrialDays(id,newDays);

    }


    //Utility
    public User getUser(int id){
        if(containsID(id)){
            return userRepository.getUser(id);
        }
        return null;
    }
    public Admin getAdmin(int id){
        if(containsID(id) && userRepository.getUser(id) instanceof Admin){
            return (Admin) userRepository.getUser(id);
        }
        return null;
    }
    public Guest getGuest(int id){

        System.out.println(containsID(id));
        if(containsID(id) && userRepository.getUser(id) instanceof Guest){
            return (Guest) userRepository.getUser(id);
        }

        return null;
    }
    public boolean containsID(int id){
        Iterator<User> it = userRepository.allUserList().iterator();
        while (it.hasNext()){
            User u=it.next();
            if(u.getId()==id){
                return true;
            }
        }
        return false;
    }
    public int lastID(){
        return userRepository.lastID();
    }
    public int nbOfDuplicates(User user){
        int count=1;
        Iterator<User> it = userRepository.allUserList().iterator();
        while (it.hasNext()){
            User u=it.next();
            if(user.getId()==u.getId() || user.getEmail().equals(u.getEmail()) || user.getBillAddress().equals(u.getBillAddress())){
                count++;
            }
        }
        return count;
    }
    public boolean duplicate(User user){
        int count=1;
        Iterator<User> it = userRepository.allUserList().iterator();
        while (it.hasNext()){
            User u=it.next();
            if(user.getId()==u.getId() || user.getBillAddress().equals(u.getBillAddress())){
                count++;
            }
        }
        if(count<2){
            return false;
        }
        return true;
    }
    public boolean duplicateMail(String mail){
        int count=1;
        Iterator<User> it = userRepository.allUserList().iterator();
        while (it.hasNext()){
            User u=it.next();
            if(mail.equals(u.getEmail())){
                count++;
            }
        }
        if(count>=2){
            return true;
        }
        return false;
    }
    public boolean validMailText(String mail){
        final Pattern EMAIL_REGEX = Pattern.compile(
                "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
                , Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(mail).matches();
    }


    public boolean validMail(String mail){
        if(duplicateMail(mail)==false && validMailText(mail)){
            return true;
        }
        return false;
    }


    public boolean validName(String name){

        String regex = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
    public boolean validUser(User user){
        if(validMail(user.getEmail()) && validName(user.getFullName())){
            if(validName(user.getFullName()) && !duplicate(user)){
                return true;
            }
        }
        return false;
    }
    public boolean removeDuplicates(){
        int count=0;
        Iterator<User> it = userRepository.allUserList().iterator();
        while (it.hasNext()){
            User b = it.next();
            if(nbOfDuplicates(b)>=3){
                count++;
                userRepository.delete(b.getId());
            }
        }
        if(count>=1){
            System.out.println("Total duplicates removed: "+count);
            return true;
        }
        return false;
    }

    public User getUserByMail(String mail){
        Iterator<User> it = userRepository.allUserList().iterator();
        while (it.hasNext()){
            User u=it.next();
            if(mail.equals(u.getEmail())){
                return u;
            }
        }
        return null;
    }
}
