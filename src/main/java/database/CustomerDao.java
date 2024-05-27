package database;

import model.Customer;
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

            String sql = "insert into Customers (username, password, fullName, numberPhone, email, role, provider, provider_user_id)" +
                    "  values (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, customer.getUsername());
            pst.setNString(2, customer.getPassword());
            pst.setNString(3, customer.getFullName());
            pst.setNString(4, customer.getNumberPhone());
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
            String sql = "Update Customers set  fullName=?, numberPhone=?, email=?, role=? where id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, customer.getFullName());
            pst.setNString(2, customer.getNumberPhone());
            pst.setNString(3, customer.getEmail());
            pst.setBoolean(4, customer.isRole());
            pst.setInt(5, customer.getId());

            int i = pst.executeUpdate();
            if (i > 0) {
                super.update(customer);
                JDBCUtil.closeConnection(con);
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Customer customer) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Delete from Customers Where id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customer.getId());
            int i = pst.executeUpdate();
            if (i > 0) {
                super.delete(customer);
                JDBCUtil.closeConnection(con);
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Customers";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getNString("username");
                String password = rs.getNString("password");
                String fullName = rs.getNString("fullName");
                String numberPhone = rs.getNString("numberPhone");
                String email = rs.getNString("email");
                boolean role = rs.getBoolean("role");
                String provider = rs.getNString("provider");
                String provider_user_id = rs.getNString("provider_user_id");
                Customer customer = new Customer(id, username, password, fullName, numberPhone, email, role, provider, provider_user_id);
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
            JDBCUtil.closeConnection(con);
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

    public void updateRole(Customer customer, int role) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Update Customers set role=? where id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, role);
            pst.setInt(2, customer.getId());
            pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.selectById(1);
        customerDao.updateRole(customer, 1);
    }
}
