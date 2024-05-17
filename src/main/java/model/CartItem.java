package model;



public class CartItem implements IModel {
    private int id;
    private Cart cart;
    private Product product;
    private int quantity;
    private String beforeData;

    public CartItem(){}
    public CartItem(int id, Cart cart, Product product, int quantity) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.beforeData = toString();
    }
    public CartItem(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.beforeData = toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cart=" + cart +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public String table() {
        return "CartItems";
    }

    @Override
    public String beforeData() {
        return beforeData;
    }

    @Override
    public String afterData() {
        return toString();
    }
}
