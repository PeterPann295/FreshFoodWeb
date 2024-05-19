package model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private double total;
    private Timestamp date;
    private String from_address;
    private String to_address;
    private double deliveryFee;
    private Timestamp deliveryDate;
    private String note;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private Voucher voucher;
    private List<OrderItem> orderItems;
    public Order() {}

    public Order(int id, Customer customer, double total, Timestamp date, String from_address, String to_address, double deliveryFee, Timestamp deliveryDate, String note, PaymentMethod paymentMethod, OrderStatus status, Voucher voucher, List<OrderItem> orderItems) {
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.date = date;
        this.from_address = from_address;
        this.to_address = to_address;
        this.deliveryFee = deliveryFee;
        this.deliveryDate = deliveryDate;
        this.note = note;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.voucher = voucher;
        this.orderItems = orderItems;
    }

    public Order(Customer customer, double total, Timestamp date, String from_address, String to_address, double deliveryFee, Timestamp deliveryDate, String note, PaymentMethod paymentMethod, OrderStatus status, Voucher voucher, List<OrderItem> orderItems) {
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.date = date;
        this.from_address = from_address;
        this.to_address = to_address;
        this.deliveryFee = deliveryFee;
        this.deliveryDate = deliveryDate;
        this.note = note;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.voucher = voucher;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getFrom_address() {
        return from_address;
    }

    public void setFrom_address(String from_address) {
        this.from_address = from_address;
    }

    public String getTo_address() {
        return to_address;
    }

    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", total=" + total +
                ", date=" + date +
                ", from_address='" + from_address + '\'' +
                ", to_address='" + to_address + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", deliveryDate=" + deliveryDate +
                ", note='" + note + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", status=" + status +
                ", voucher=" + voucher +
                ", orderItems=" + orderItems +
                '}';
    }
}
