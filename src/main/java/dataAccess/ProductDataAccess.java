package dataAccess;

import model.Data;
import model.Product;
import model.User;

import javax.swing.*;
import java.sql.ResultSet;

public class ProductDataAccess {

    public static Product getProducts(){

        String query = "select * from product";
        Product product = null;
        try{
            ResultSet res = DbOperations.getData(query);
            while(res.next()){
                product = new Product();
                product.setProductId(res.getString("productId"));
                product.setProductName(res.getString("productName"));
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
            while(res.next()){
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

}
