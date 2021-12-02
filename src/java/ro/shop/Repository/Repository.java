package ro.shop.Repository;

import java.sql.*;
import java.util.List;

public abstract class Repository {
    protected String jdbcURL="jdbc:mysql://localhost:3306/mockup_online_shop";
    protected String username="root";
    protected String password="root";

    protected Connection connection=null;
    protected Statement statement=null;

    public Repository(){
        try {
            connection= DriverManager.getConnection(jdbcURL,username,password);
            statement=connection.createStatement();
        }catch (Exception e){
            System.out.println("Error connecting to database from Repository");
        }
    }

    protected boolean execute(String execute){
        try {
            statement.execute(execute);
            return true;
        }catch (SQLException e){
            System.out.println("Execute didn't work \n"+execute);
            return false;
        }
    }


    public abstract ResultSet allObjects();
    public abstract ResultSet byID(int id);
    public abstract ResultSet resultSetLastID();













}
