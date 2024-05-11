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
                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Customer customer) {
        return super.update(customer);
    }

    @Override
    public int delete(Customer customer) {
        return super.delete(customer);
    }

    @Override
    public ArrayList<Customer> selectAll() {
        return super.selectAll();
    }

    @Override
    public Customer selectById(int id) {
        return super.selectById(id);
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
            }
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

}
