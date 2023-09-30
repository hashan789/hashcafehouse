package com.example.hashcafehouse;

import alerts.AlertMessages;
import dataAccess.CustomerDataAccess;
import dataAccess.ProductDataAccess;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Customer;
import model.Data;
import model.Item;
import model.Product;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ProductCardController implements Initializable {

    @FXML
    private Button addItemBtn;

    @FXML
    private Spinner<Integer> amountItems;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;

    private Item itemData;
    private Image image;

    private int quantity;

    private SpinnerValueFactory<Integer> spin;

    private double totalPrice;
    private double pr;
    
    private String productId;
    private String type;
    private String productDate;
    private Date date;

    public void setData(Item itemData){
        this.itemData = itemData;

        productId = itemData.getItemId();
        itemName.setText(itemData.getItemName());
        itemPrice.setText(String.valueOf(itemData.getPrice()));
        String path = "File:" + itemData.getImage();
        image = new Image(path,190,94,false,true);
        itemImage.setImage(image);
        pr = itemData.getPrice();

        Product product = ProductDataAccess.getProductsWithName(itemData.getItemName());
        setProductData(product);
    }
    
    public void setProductData(Product product){

        date = product.getDate();
        productDate = String.valueOf(product.getDate());
        type = product.getType();
        productId = product.getProductId();
    }

    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        amountItems.setValueFactory(spin);
    }



    @FXML
    public void addItems(){

        MainFormController mainForm = new MainFormController();
        mainForm.customerId();

        quantity = amountItems.getValue();
        
        int checkStock = 0;

        Product product = ProductDataAccess.getStock(itemName.getText());
        checkStock = product.getStock();

        if(checkStock == 0){
            product.setProductName(itemName.getText());
            product.setType(type);
            product.setImage(image.getUrl());
            product.setDate(date);
            ProductDataAccess.updateUnavailableStock(product,Data.cID);
        }
        
        if(checkStock < quantity){
            AlertMessages alert = new AlertMessages();
            alert.errorMessage("error","Error Message","Invalid. This product is out of stock");
        }
        else{
            Customer customer = new Customer();
            customer.setCustomerId(Data.cID);
            customer.setProductName(itemName.getText());
            customer.setQuantity(quantity);
            totalPrice = (quantity * pr);

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            customer.setDate(sqlDate);
            customer.setUsername(Data.username);
            customer.setPrice(totalPrice);
            ProductDataAccess.getStatus(itemName.getText(),quantity,customer);

         //   CustomerDataAccess.save(customer);

            int updateStock = checkStock - quantity;

            ProductDataAccess.updateStock(updateStock,itemName.getText());

            AlertMessages alert = new AlertMessages();
            alert.errorMessage("information","Information Message","Successfully Added!");


            mainForm.menuDisplayTotal();
        }
        
        if(quantity == 0){

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
    }
}
