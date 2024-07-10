package model;

public class Customer implements IModel {
        private int id;
        private String username;
        private String password;
        private String fullName;
        private String numberPhone;
        private String email;
        private boolean role;

        private String provider;
        private String provider_user_id;
        private String beforeData;

        public Customer(){
        }

        public Customer(int id, String username, String password, String fullName, String numberPhone, String email, boolean role) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.numberPhone = numberPhone;
            this.email = email;
            this.role = role;
            this.beforeData = toString();
            this.provider = null;
            this.provider_user_id = null;
        }
        public Customer(String username, String password, String fullName, String numberPhone , String email) {
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.numberPhone = numberPhone;
            this.email = email;
            this.role = false;
            this.beforeData = toString();
            this.provider = null;
            this.provider_user_id = null;
        }

        public Customer(int id, String username, String password, String fullName, String numberPhone, String email, boolean role, String provider, String provider_user_id) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.numberPhone = numberPhone;
            this.email = email;
            this.role = role;
            this.provider = provider;
            this.provider_user_id = provider_user_id;
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

        public String getProvider() {
        return provider;
    }

        public void setProvider(String provider) {
        this.provider = provider;
    }

        public String getProvider_user_id() {
        return provider_user_id;
    }

        public void setProvider_user_id(String provider_user_id) {
        this.provider_user_id = provider_user_id;
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
            return "customers";
        }

        @Override
        public String beforeData() {
            return beforeData;
        }

    public void setBeforeData(String beforeData) {
        this.beforeData = beforeData;
    }

    @Override
        public String afterData() {
            return toString();
        }
}
