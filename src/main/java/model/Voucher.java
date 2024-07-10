package model;

public class Voucher {
    private int id;
    private String code;
    private double discount;

    public Voucher(int id, String name, double discount) {
        this.id = id;
        this.code = name;
        this.discount = discount;
    }

    public Voucher() {
    }

    public Voucher(String name, double discount) {
        this.code = name;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String name) {
        this.code = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                '}';
    }
}
