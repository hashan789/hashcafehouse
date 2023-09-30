package dataAccess;

import alerts.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import javax.swing.*;
import java.sql.ResultSet;

public class ProductDataAccess {

    public static ObservableList<ProductTable> getProducts(){

        ObservableList<ProductTable> listData = FXCollections.observableArrayList();

        String query = "select * from product";
        ProductTable productTable = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                productTable = new ProductTable(res.getInt("id"),res.getString("productId"),res.getString("productName"),res.getString("type"),String.valueOf(res.getInt("stock")),String.valueOf(res.getInt("price")),String.valueOf(res.getDate("date")),res.getString("image"));
                listData.add(productTable);
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return listData;

    }

    public static Product getProductsWithName(String name){

        String query = "select * from product where productName = '"+ name +"'";
        Product product = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                product = new Product();
                product.setProductId(res.getString("productId"));
                product.setProductName(res.getString("productName"));
                product.setType(res.getString("type"));
                product.setStock(res.getInt("stock"));
                product.setPrice(res.getInt("price"));
                product.setId(res.getInt("id"));
                product.setStatus(res.getString("status"));
                product.setDate(res.getDate("date"));
                product.setImage(res.getString("image"));
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return product;

    }

    public static Product getProductId(String id){
        String query = "select productId from product where productId = '"+ id +"'";
        Product product = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                product = new Product();
                product.setProductId(res.getString("productId"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return product;
    }

    public static void save(Product product){
            String query = "insert into product(productId,productName,type,stock,status,price,image,date) values" +
                    "(" +
                    "'"+product.getProductId()+"'," +
                    "'"+product.getProductName()+"'," +
                    "'"+product.getType()+"'," +
                    "'"+product.getStock()+"'," +
                    "'"+product.getStatus()+"'," +
                    "'"+product.getPrice()+"'," +
                    "'"+product.getImage()+"'," +
                    "'"+product.getDate()+"')";
            DbOperations.setDataOrDelete(query, "Added product Successfully!");


    }

    public static void update(Product product,Integer id){
        String query = "update product set productId = '"+ product.getProductId() +"'," +
                "productName = '"+ product.getProductName() +"', type = '" + product.getType() +"'," +
                "stock = '"+ product.getStock() +"'," +
                "price = '"+ product.getPrice() +"'," +
                "status = '"+ product.getStatus() +"'," +
                "image = '"+ product.getImage() +"'," +
                "date = '"+product.getDate()+"' where id = " + id;
        DbOperations.setDataOrDelete(query, "Updated product Successfully!");


    }

    public static void delete(Integer id){
        String query = "delete from product where id = " + id;
        DbOperations.setDataOrDelete(query, "Deleted product Successfully!");
    }

    public static ObservableList<Item> getItems(){

        ObservableList<Item> listItemData = FXCollections.observableArrayList();

        String query = "select * from product";
        Item item = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                item = new Item();
                item.setItemId(res.getString("productId"));
                item.setItemName(res.getString("productName"));
                item.setPrice(res.getInt("price"));
                item.setId(res.getInt("id"));
                item.setImage(res.getString("image"));
                listItemData.add(item);
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return listItemData;

    }



    public static Product getStatus(String name, Integer quantity, Customer customer){

        String check = "";

        String query = "select status from product where productName = '"+ name +"'";
        Product product = null;
        try{
            ResultSet res = DbOperations.getData(query);
            if(res.next()){
                product = new Product();
                product.setStatus(res.getString("status"));
            }

            check = product.getStatus();

            if(check.equals("Unavailable") || quantity == 0){
                AlertMessages alert = new AlertMessages();
                alert.errorMessage("error","Error Message","Something is Wrong");
            }
            else{
                CustomerDataAccess.save(customer);


            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return product;

    }

    public static Product getStock(String name){

        String query = "select stock from product where productName = '"+ name +"'";
        Product product = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                product = new Product();
                product.setStock(res.getInt("stock"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        return product;
    }

    public static void updateStock(int stock,String name){
        String query = "update product set stock = '"+ stock +"' where productName = '"+ name +"'";
        DbOperations.setDataOrDelete(query, "Updated product Successfully!");


    }

    public static void updateUnavailableStock(Product product,Integer id){
        String query = "update product set" +
                "productName = '"+ product.getProductName() +"', type = '" + product.getType() +"'," +
                "stock = 0," +
                "price = '"+ product.getPrice() +"'," +
                "status = 'Unavailable'," +
                "image = '"+ product.getImage() +"'," +
                "date = '"+product.getDate()+"' where id = " + id;
        DbOperations.setDataOrDelete(query, "Updated product Successfully!");


    }









}
