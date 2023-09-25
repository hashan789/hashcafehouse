package dataAccess;

import model.Customer;
import model.Product;
import model.Receipt;
import model.ReceiptTable;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.Date;

public class ReceiptDataAccess {

    public static Receipt gerMaxId(){
        String query = "select max(customerId) from receipt";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                receipt.setCustomerId(res.getInt("customerId"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }

    public static void save(Receipt receipt){
        String query = "insert into receipt(customerId,total,date,username) values" +
                "(" +
                "'"+receipt.getCustomerId()+"'," +
                "'"+receipt.getTotal()+"'," +
                "'"+receipt.getDate()+"'," +
                "'"+receipt.getUsername()+"')";
        DbOperations.setDataOrDelete(query, "Added product Successfully!");


    }

    public static ReceiptTable getReciepts(){
        String query = "select * from receipt";
        ReceiptTable receiptTable = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                receiptTable = new ReceiptTable(String.valueOf(res.getInt("customerId")),String.valueOf(res.getDouble("total")),String.valueOf(res.getDate("date")),res.getString("username"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receiptTable;
    }

    public static Receipt getCountId(){
        String query = "select COUNT(id) from receipt";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                receipt.setId(res.getInt("COUNT(id)"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }

    public static Receipt getSumId(){

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String query = "select SUM(total) from receipt where date = '"+ date +"'";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                receipt.setTotal(res.getInt("SUM(total)"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }

    public static Receipt getSumIdWithdate(){

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String query = "select SUM(total) from receipt where date = '"+ date +"'";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                receipt.setTotal(res.getInt("SUM(total)"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }

    public static Receipt getSumTotalAndDate(){

        String query = "select SUM(total), date from receipt group by date order by TIMESTAMP(date)";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                receipt.setTotal(res.getInt("SUM(total)"));
                receipt.setDate(res.getDate("date"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }


}
