package dataAccess;

import javax.swing.*;

public class tables {
    public static void main(String[] args){
        try{
            String userTable = "create table user(" +
                    "id int AUTO_INCREMENT primary key," +
                    "name varchar(200)," +
                    "email varchar(200)," +
                    "address varchar(200)," +
                    "password varchar(200)," +
                    "UNIQUE (email))";
            DbOperations.setDataOrDelete(userTable, "User Table Created Successfully");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        }
    }

