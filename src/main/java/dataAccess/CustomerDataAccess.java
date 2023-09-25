package dataAccess;

import model.Customer;
import model.CustomerTable;
import model.Product;
import model.Receipt;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.Date;

public class CustomerDataAccess {

    public static Customer getCustomerDetails(){
        String query = "select * from customer";
        Customer customer = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                customer.setId(res.getInt("id"));
                customer.setCustomerId(res.getInt("customerId"));
                customer.setProductName(res.getString("productName"));
                customer.setQuantity(res.getInt("quantity"));
                customer.setPrice(res.getInt("price"));
                customer.setDate(res.getDate("date"));
                customer.setUsername(res.getString("username"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }

    public static void save(Customer customer){
        String query = "insert into customer(customerId,productName,quantity,price,date,username) values" +
                "(" +
                "'"+customer.getCustomerId()+"'," +
                "'"+customer.getProductName()+"'," +
                "'"+customer.getQuantity()+"'," +
                "'"+customer.getPrice()+"'," +
                "'"+customer.getDate()+"'," +
                "'"+customer.getUsername()+"')";
        DbOperations.setDataOrDelete(query, "Registered Successfully! Wait for Admin Approval!");

    }

    public static Customer getMaxId(){
        String query = "select max(customerId) from customer";
        Customer customer = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                customer.setCustomerId(res.getInt("customerId"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }

    public static Customer getTotal(int id,Double total){

        String query = "select SUM(price) from customer where customerId = " + id;
        Customer customer = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                customer.setPrice(res.getInt("SUM(price)"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }

    public static CustomerTable getItemsWhereId(int id){

        String query = "select * from customer where customerId = " + id;
        CustomerTable customerTable = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                customerTable = new CustomerTable(res.getString("productName"),String.valueOf(res.getString("quantity")),String.valueOf(res.getString("price")));
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return customerTable;

    }

    public static void delete(Integer id){
        String query = "delete from customer where id = " + id;
        DbOperations.setDataOrDelete(query, "Deleted product Successfully!");
    }

    public static Customer getQuantity(){

        String query = "select COUNT(quantity) from customer";
        Customer customer = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                customer.setQuantity(res.getInt("SUM(total)"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }


}
