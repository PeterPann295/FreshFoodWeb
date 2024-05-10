package model;

public class Customer implements IModel {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String numberPhone;
    private boolean role;
    private String beforeData;

    public Customer(int id, String username, String password, String fullName, String email, String numberPhone, boolean role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.role = role;
        this.beforeData = toString();
    }
    public Customer(String username, String password, String fullName, String email, String numberPhone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.role = false;
        this.beforeData = toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public String table() {
        return "Customers";
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
