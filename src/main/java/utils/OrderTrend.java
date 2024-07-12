package utils;

import model.Customer;

public class OrderTrend {
    private Customer customer;
    private int orderQuantityThisMonth;
    private int orderQuantityLastMonth;
    private int orderQuantityTwoMonthAgo;
    private double totalThisMonth;
    private double totalLastMonth;
    private double totalTwoMonthAgo;
    private double totalAmount;

    public OrderTrend(Customer customer, int orderQuantityThisMonth, int orderQuantityLastMonth, int orderQuantityTwoMonthAgo, double totalThisMonth, double totalLastMonth, double totalTwoMonthAgo, double totalAmount) {
        this.customer = customer;
        this.orderQuantityThisMonth = orderQuantityThisMonth;
        this.orderQuantityLastMonth = orderQuantityLastMonth;
        this.orderQuantityTwoMonthAgo = orderQuantityTwoMonthAgo;
        this.totalThisMonth = totalThisMonth;
        this.totalLastMonth = totalLastMonth;
        this.totalTwoMonthAgo = totalTwoMonthAgo;
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOrderQuantityThisMonth() {
        return orderQuantityThisMonth;
    }

    public void setOrderQuantityThisMonth(int orderQuantityThisMonth) {
        this.orderQuantityThisMonth = orderQuantityThisMonth;
    }

    public int getOrderQuantityLastMonth() {
        return orderQuantityLastMonth;
    }

    public void setOrderQuantityLastMonth(int orderQuantityLastMonth) {
        this.orderQuantityLastMonth = orderQuantityLastMonth;
    }

    public int getOrderQuantityTwoMonthAgo() {
        return orderQuantityTwoMonthAgo;
    }

    public void setOrderQuantityTwoMonthAgo(int orderQuantityTwoMonthAgo) {
        this.orderQuantityTwoMonthAgo = orderQuantityTwoMonthAgo;
    }

    public double getTotalThisMonth() {
        return totalThisMonth;
    }

    public void setTotalThisMonth(double totalThisMonth) {
        this.totalThisMonth = totalThisMonth;
    }

    public double getTotalLastMonth() {
        return totalLastMonth;
    }

    public void setTotalLastMonth(double totalLastMonth) {
        this.totalLastMonth = totalLastMonth;
    }

    public double getTotalTwoMonthAgo() {
        return totalTwoMonthAgo;
    }

    public void setTotalTwoMonthAgo(double totalTwoMonthAgo) {
        this.totalTwoMonthAgo = totalTwoMonthAgo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
