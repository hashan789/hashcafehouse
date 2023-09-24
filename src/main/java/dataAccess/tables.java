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
            String productTable = "create table product(" +
                    "id int AUTO_INCREMENT primary key," +
                    "productId varchar(200)," +
                    "productName varchar(200)," +
                    "type varchar(200)," +
                    "stock int," +
                    "status varchar(200)," +
                    "price double," +
                    "image varchar(200)," +
                    "date date," +
                    "UNIQUE (id))";
            String customerTable = "create table customer(" +
                    "id int AUTO_INCREMENT primary key," +
                    "customerId int," +
                    "productName varchar(200)," +
                    "quantity int," +
                    "price double," +
                    "date date," +
                    "username varchar(200)," +
                    "UNIQUE (id))";
            String receiptTable = "create table receipt(" +
                    "id int AUTO_INCREMENT primary key," +
                    "customerId int," +
                    "total double," +
                    "date date," +
                    "username varchar(200)," +
                    "UNIQUE (id))";
            DbOperations.setDataOrDelete(userTable, "User Table Created Successfully");
            DbOperations.setDataOrDelete(productTable, "product Table Created Successfully");
            DbOperations.setDataOrDelete(customerTable, "customer Table Created Successfully");
            DbOperations.setDataOrDelete(receiptTable, "Receipt Table Created Successfully");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        }
    }

