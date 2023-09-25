package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductTable {

    private SimpleIntegerProperty id;
    private SimpleStringProperty productId;
    private SimpleStringProperty productName;
    private SimpleStringProperty type;
    private SimpleStringProperty stock;
    private SimpleStringProperty price;
    private SimpleStringProperty status;
    private SimpleStringProperty date;
    private SimpleStringProperty image;

    public ProductTable(String productId, String productName, String type, String stock, String price, String date){
        this.productId = new SimpleStringProperty(productId);
        this.productName = new SimpleStringProperty(productName);
        this.type = new SimpleStringProperty(type);
        this.stock = new SimpleStringProperty(stock);
        this.price = new SimpleStringProperty(price);
        this.date = new SimpleStringProperty(date);
    }

    public String getStock() {
        return stock.get();
    }

    public SimpleStringProperty stockProperty() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock.set(stock);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getProductId() {
        return productId.get();
    }

    public SimpleStringProperty productIdProperty() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId.set(productId);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }



    public String getImage() {
        return image.get();
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }
}
