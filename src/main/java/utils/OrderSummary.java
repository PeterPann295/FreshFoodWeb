package utils;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderSummary {
    private Date orderDate;
    private double totalAmount;

    public OrderSummary(Date orderDate, double totalAmount) {
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

