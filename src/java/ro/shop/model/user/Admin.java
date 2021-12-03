package ro.shop.model.user;

import ro.shop.model.User;

public class Admin extends User {
    private boolean adminAccess;
    private String adminPassword;


    public Admin(int id, String email, String password, String fullName, String billAddress, boolean adminAccess, String adminPassword) {
        super(id, email, password, fullName, billAddress);
        this.adminAccess = adminAccess; //after login with adminPass, set true
        this.adminPassword = adminPassword;
    }

    public Admin(String email, String password, String fullName, String billAddress, boolean adminAccess, String adminPassword) {
        super(email, password, fullName, billAddress);
        this.adminAccess = adminAccess; //after login with adminPass, set true
        this.adminPassword = adminPassword;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public boolean isAdminAccess() {
        return adminAccess;
    }

    public void setAdminAccess(boolean adminAccess) {
        this.adminAccess = adminAccess;
    }


    @Override
    public int compareTo(User admin){
        if(admin.getId()==this.getId()){
            return 0;
        }else if(admin.getId()<this.getId()){
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj){
        Admin admin = (Admin) obj;
        return admin.getId()==this.getId();
    }

    @Override
    public String toString(){
        String prod = super.toString();
        return prod+","+this.isAdminAccess()+","+this.getAdminPassword();
    }
}
