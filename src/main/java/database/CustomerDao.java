package database;

import model.Customer;
import utils.Encode;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDao extends AbsDao<Customer> {
    @Override
    public int insert(Customer customer) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO Customers (username, password, fullName, numberPhone, email, role, provider, provider_user_id) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setNString(1, customer.getUsername());
            pst.setNString(2, customer.getPassword());
            pst.setNString(3, customer.getFullName());

            // Kiểm tra và cắt ngắn chuỗi số điện thoại nếu cần
            String numberPhone = customer.getNumberPhone();
            if (numberPhone.length() > 15) {
                numberPhone = numberPhone.substring(0, 15);
            }
            pst.setNString(4, numberPhone);

            pst.setNString(5, customer.getEmail());
            pst.setBoolean(6, customer.isRole());
            pst.setNString(7, customer.getProvider());
            pst.setNString(8, customer.getProvider_user_id());

            int i = pst.executeUpdate();
            if (i > 0) {
                super.insert(customer);
                JDBCUtil.closeConnection(con);
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int update(Customer customer) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE Customers SET username = ?, password = ?, fullName = ?, numberPhone = ?, email = ?, role = ?, provider = ?, provider_user_id = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, customer.getUsername());
            pst.setNString(2, customer.getPassword());
            pst.setNString(3, customer.getFullName());
            pst.setNString(4, customer.getNumberPhone());
            pst.setNString(5, customer.getEmail());
            pst.setBoolean(6, customer.isRole());
            pst.setNString(7, customer.getProvider());
            pst.setNString(8, customer.getProvider_user_id());
            pst.setInt(9, customer.getId()); // Thiết lập giá trị cho customerId để xác định bản ghi cần cập nhật
            int i = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            if (i > 0) {
                super.update(customer);
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Customer customer) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM Customers WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customer.getId());
            int i = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            if (i > 0) {
                super.delete(customer);
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from customers";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setFullName(rs.getString("fullName"));
                customer.setNumberPhone(rs.getString("numberPhone"));
                customer.setEmail(rs.getString("email"));
                customer.setRole(rs.getBoolean("role"));
                customer.setProvider(rs.getString("provider"));
                customer.setProvider_user_id(rs.getString("provider_user_id"));
                customers.add(customer);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer selectById(int id) {
        Customer customer = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Customers Where id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setFullName(rs.getString("fullName"));
                customer.setNumberPhone(rs.getString("numberPhone"));
                customer.setEmail(rs.getString("email"));
                customer.setRole(rs.getBoolean("role"));
                customer.setProvider(rs.getString("provider"));
                customer.setProvider_user_id(rs.getString("provider_user_id"));
                customer.setBeforeData(customer.toString());
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public boolean checkUsername(String username) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Customers Where username=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Customer checkProviderUserID(String providerUserId) {
        Customer c = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Customers Where provider_user_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, providerUserId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c = new Customer();
                c.setId(rs.getInt("id"));
                c.setFullName(rs.getString("fullName"));
                c.setEmail(rs.getString("email"));
                c.setProvider(rs.getString("provider"));
                c.setProvider_user_id(rs.getString("provider_user_id"));
                super.user_id = c.getId();
                super.login(c);
            }
            JDBCUtil.closeConnection(con);
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public Customer checkLogin(String username, String password) {
        Customer c = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Customers Where username=? and password=?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String user_name = rs.getString("username");
                String password_ = rs.getString("password");
                String nameCustomer_ = rs.getString("fullName");
                String numberPhone_ = rs.getString("numberPhone");
                String email = rs.getString("email");
                boolean role = rs.getBoolean("role");
                String provider = rs.getNString("provider");
                String providerUserId = rs.getNString("provider_user_id");
                c = new Customer(id,user_name, password_, nameCustomer_, numberPhone_, email, role, provider, providerUserId);

                super.user_id = c.getId();
                super.login(c);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public int updateResetCode(String resetCode, String username){
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE Customers SET reset_code=? WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(resetCode));
            pst.setString(2, username);
            int i = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            if (i > 0) {
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean checkResetCode(String username, String resetCode) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Customers Where username=? and reset_code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, resetCode);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Customer selectByUsername(String username) {
        Customer customer = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Customers Where username=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setFullName(rs.getString("fullName"));
                customer.setNumberPhone(rs.getString("numberPhone"));
                customer.setEmail(rs.getString("email"));
                customer.setRole(rs.getBoolean("role"));
                customer.setProvider(rs.getString("provider"));
                customer.setProvider_user_id(rs.getString("provider_user_id"));
                customer.setBeforeData(customer.toString());
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public ArrayList<Customer> selectAdmin() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from customers where role=1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setFullName(rs.getString("fullName"));
                customer.setNumberPhone(rs.getString("numberPhone"));
                customer.setEmail(rs.getString("email"));
                customer.setRole(rs.getBoolean("role"));
                customer.setProvider(rs.getString("provider"));
                customer.setProvider_user_id(rs.getString("provider_user_id"));
                customers.add(customer);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
        System.out.println(customerDao.selectAdmin().size());
    }

}