package model;

public class Discount implements IModel {
    private int id;
    private String name;
    private int percent;
    private String beforeData;
    public Discount(){}

    public Discount(int id, String name, int percent) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        beforeData = toString();
    }
    public Discount(String name, int percent) {
        this.name = name;
        this.percent = percent;
        beforeData = toString();
    }


    public Discount(int discountId) {
        this.id = discountId;
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

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percent=" + percent +
                '}';
    }

    @Override
    public String table() {
        return "discounts";
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
