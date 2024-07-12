package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private Customer customer;
    private double totalPrice;
    private List<CartItem> cartItems;
    public Cart(){}
    public Cart(int id, Customer customer, double totalPrice) {
        this.id = id;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.cartItems = new ArrayList<CartItem>();
    }
    public Cart(int id, Customer customer, double totalPrice, List<CartItem> cartItems) {
        this.id = id;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }
    public Cart( Customer customer, double totalPrice, List<CartItem> cartItems) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public int getTotalQuantity() {
        int result = 0;
        for (CartItem cartItem : cartItems) {
            result += cartItem.getQuantity();
        }
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
