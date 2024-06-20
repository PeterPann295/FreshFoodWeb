package model;

public class Product implements IModel {

    private int id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private String unit;
    private double weight;
    private boolean available;
    private Category category;
    private Discount discount;
    private String beforeData;
    public Product() {}

    public Product(String name, String description, double price, String imageUrl, String unit, double weight, boolean available, Category category, Discount discount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.unit = unit;
        this.weight = weight;
        this.available = available;
        this.category = category;
        this.discount = discount;
        this.beforeData = toString();
    }

    public Product(int id, String name, String description, double price, String imageUrl, String unit, double weight, boolean available, Category category, Discount discount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.unit = unit;
        this.weight = weight;
        this.available = available;
        this.category = category;
        this.discount = discount;
        this.beforeData = toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                ", available=" + available +
                ", category=" + category +
                ", discount=" + discount +
                '}';
    }
    @Override
    public String table() {
        return "products";
    }

    @Override
    public String beforeData() {
        return beforeData;
    }

    @Override
    public String afterData() {
        return toString();
    }
    public double getFinalPrice() {
        if (discount != null) {
            double pc = (double) discount.getPercent() / 100;
            double discountAmount = price * pc;
            return price - discountAmount;
        } else {
            return price;
        }
    }

}