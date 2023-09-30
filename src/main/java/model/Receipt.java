package model;

import java.util.Date;

public class Receipt {

    private int id;
    private int customerId;
    private String username;
    private double total;
    private float sumTotal;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public float getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(float sumTotal) {
        this.sumTotal = sumTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
