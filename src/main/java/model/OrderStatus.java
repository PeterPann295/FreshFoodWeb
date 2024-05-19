package model;

public class OrderStatus {
    private int id;
    private String name;

    public OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
