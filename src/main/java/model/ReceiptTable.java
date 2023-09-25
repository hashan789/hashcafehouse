package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReceiptTable {

    private SimpleStringProperty customerId;
    private SimpleStringProperty total;
    private SimpleStringProperty date;
    private SimpleStringProperty username;

    public ReceiptTable(String customerId,String total, String date, String username){
        this.customerId = new SimpleStringProperty(customerId);
        this.total = new SimpleStringProperty(total);
        this.date = new SimpleStringProperty(date);
        this.username = new SimpleStringProperty(username);
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public SimpleStringProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
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

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
}
