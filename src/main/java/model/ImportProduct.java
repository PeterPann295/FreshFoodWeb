package model;

import java.sql.Timestamp;

public class ImportProduct {
    private int id;
    private Product product;
    private int quatity;
    private Timestamp date;
    private Customer customer;

    public ImportProduct(int id, Product product, int quatity, Timestamp date, Customer customer) {
        this.id = id;
        this.product = product;
        this.quatity = quatity;
        this.date = date;
        this.customer = customer;
    }
    public ImportProduct(Product product, int quatity, Customer customer) {
        this.product = product;
        this.quatity = quatity;
        this.date = new Timestamp(System.currentTimeMillis());
        this.customer = customer;
    }
    public ImportProduct() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ImportProduct{" +
                "id=" + id +
                ", product=" + product +
                ", quatity=" + quatity +
                ", date=" + date +
                '}';
    }
}
