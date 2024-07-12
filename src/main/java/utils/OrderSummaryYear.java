package utils;

public class OrderSummaryYear {
    private int year;
    private double totalAmount;

    public OrderSummaryYear(int year, double totalAmount) {
        this.year = year;
        this.totalAmount = totalAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
