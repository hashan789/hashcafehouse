package dataAccess;
import model.User;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.*;

public class UserDataAccess {

    public static void save(User user){
        String query = "insert into user(name,email,address,password) values" +
                "(" +
                "'"+user.getUsername()+"'," +
                "'"+user.getEmail()+"'," +
                "'"+user.getAddress()+"'," +
                "'"+user.getPassword()+"')";
        DbOperations.setDataOrDelete(query, "Registered Successfully! Wait for Admin Approval!");

    }

    public static User login(String username, String password){
        User user = null;
        try{
            ResultSet res = DbOperations.getData("select * from user where name='"+username+"' and password='"+password+"'");
            while(res.next()){
                user = new User();
                user.setEmail(res.getString("email"));
                user.setUsername(res.getString("name"));
                user.setPassword(res.getString("password"));
                user.setAddress(res.getString("address"));
                user.setId(res.getInt("id"));
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return user;

    }

    public static void update(String username, String password){
        String query = "update user set password = '"+ password +"' where nam e ='"+ username +"'";
        DbOperations.setDataOrDelete(query,"changed password successfully!");
    }
}
