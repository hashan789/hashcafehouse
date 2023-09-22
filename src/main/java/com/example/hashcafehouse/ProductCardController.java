package com.example.hashcafehouse;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductCardController{

    @FXML
    private Button addItemBtn;

    @FXML
    private Spinner<?> amountItems;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;

    private Item itemData;
    private Image image;

    public void setData(Item itemData){
        this.itemData = itemData;

        itemName.setText(itemData.getItemName());
        itemPrice.setText(String.valueOf(itemData.getPrice()));
        String path = "File:" + itemData.getImage();
        image = new Image(itemData.getImage(),190,94,false,true);
        itemImage.setImage(image);
    }

    @FXML
    public void addItems(){

    }
}
