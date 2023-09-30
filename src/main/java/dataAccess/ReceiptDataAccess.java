package dataAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Product;
import model.Receipt;
import model.ReceiptTable;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.Date;

public class ReceiptDataAccess {

    public static Receipt getMaxId(){
        String query = "select max(customerId) as maxCustomerId from receipt";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                receipt = new Receipt();
                receipt.setCustomerId(res.getInt("maxCustomerId"));
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
        DbOperations.setDataOrDelete(query, "Added Receipt Successfully!");


    }

    public static ObservableList<ReceiptTable> getReciepts(){

        ObservableList<ReceiptTable> listData = FXCollections.observableArrayList();

        String query = "select * from receipt";
        ReceiptTable receiptTable = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                receiptTable = new ReceiptTable(String.valueOf(res.getInt("customerId")),String.valueOf(res.getDouble("total")),String.valueOf(res.getDate("date")),res.getString("username"));
                listData.add(receiptTable);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return listData;
    }

    public static Receipt getCountId(){
        String query = "select COUNT(id) as totalId from receipt";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                receipt = new Receipt();
                receipt.setId(res.getInt("totalId"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }

    public static Receipt getSumId(){

        String query = "select SUM(total) as sumTotal from receipt";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                receipt = new Receipt();
                receipt.setSumTotal(res.getFloat("sumTotal"));
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

        String query = "select SUM(total) as sumTotal from receipt where date = '"+ sqlDate +"'";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                receipt = new Receipt();
                receipt.setSumTotal(res.getFloat("sumTotal"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }

    public static Receipt getSumTotalAndDate(){

        String query = "select SUM(total) as sumTotal, date from receipt group by date order by TIMESTAMP(date)";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                receipt = new Receipt();
                receipt.setSumTotal(res.getFloat("sumTotal"));
                receipt.setDate(res.getDate("date"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }

    public static Receipt getSumIdAndDate(){

        String query = "select SUM(customerId) as sumId, date from receipt group by date order by TIMESTAMP(date)";
        Receipt receipt = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                receipt = new Receipt();
                receipt.setId(res.getInt("sumId"));
                receipt.setDate(res.getDate("date"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return receipt;
    }


}
