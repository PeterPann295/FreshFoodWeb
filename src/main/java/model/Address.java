package model;

public class Address {
    private int id;
    private Customer customer;
    private String nameRecipient;
    private String numberRecipient;
    private int provinceId;
    private int districtId;
    private int wardId;
    private String detailAdress;

    public Address(int id, Customer customer, String nameRecipient, String numberRecipient, int provinceId, int districtId, int wardId, String detailAdress) {
        this.id = id;
        this.customer = customer;
        this.nameRecipient = nameRecipient;
        this.numberRecipient = numberRecipient;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.detailAdress = detailAdress;
    }
    public Address(Customer customer, String nameRecipient, String numberRecipient, int provinceId, int districtId, int wardId, String detailAdress) {
        this.customer = customer;
        this.nameRecipient = nameRecipient;
        this.numberRecipient = numberRecipient;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.detailAdress = detailAdress;
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

    public String getNameRecipient() {
        return nameRecipient;
    }

    public void setNameRecipient(String nameRecipient) {
        this.nameRecipient = nameRecipient;
    }

    public String getNumberRecipient() {
        return numberRecipient;
    }

    public void setNumberRecipient(String numberRecipient) {
        this.numberRecipient = numberRecipient;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getDetailAdress() {
        return detailAdress;
    }

    public void setDetailAdress(String detailAdress) {
        this.detailAdress = detailAdress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", customer=" + customer +
                ", nameRecipient='" + nameRecipient + '\'' +
                ", numberRecipient='" + numberRecipient + '\'' +
                ", provinceId=" + provinceId +
                ", districtId=" + districtId +
                ", wardId=" + wardId +
                ", detailAdress='" + detailAdress + '\'' +
                '}';
    }
}
