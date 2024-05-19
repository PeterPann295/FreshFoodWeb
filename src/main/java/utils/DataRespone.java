package utils;

public class DataRespone {
    private String deliveryFee;
    private String deliveryDate;

    public DataRespone() {}
    public DataRespone(String deliveryFee, String deliveryDate) {
        this.deliveryFee = deliveryFee;
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "DataRespone{" +
                "deliveryFee='" + deliveryFee + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }
}
