package database;

import model.Cart;
import model.Customer;
import model.Discount;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CartDao implements IDao<Cart>{
    @Override
    public int insert(Cart cart) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO Carts(customer_id, totalPrice) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cart.getCustomer().getId());
            pst.setDouble(2, cart.getTotalPrice());
            int i = pst.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Cart cart) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE Carts SET customer_id = ?, total_price = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cart.getCustomer().getId());
            pst.setDouble(2, cart.getTotalPrice());
            pst.setInt(3, cart.getId());
            int i = pst.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Cart cart) {
        return 0;
    }

    @Override
    public ArrayList<Cart> selectAll() {
        return null;
    }

    @Override
    public Cart selectById(int id) {
        Cart cart = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from carts where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            CustomerDao cusDao = new CustomerDao();
            while(rs.next()) {
                int cartId = rs.getInt("id");
                Customer cus = cusDao.selectById(rs.getInt("customer_id"));
                double totalPrice = rs.getFloat("totalPrice");
                cart = new Cart(cartId, cus, totalPrice);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }
    public Cart selectByCustomerId(int customer_id) {
        Cart cart = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from carts where customer_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customer_id);
            ResultSet rs = pst.executeQuery();
            CustomerDao cusDao = new CustomerDao();
            CartItemDao ciDao = new CartItemDao();
            while(rs.next()) {
                int cartId = rs.getInt("id");
                Customer cus = cusDao.selectById(rs.getInt("customer_id"));
                double totalPrice = rs.getFloat("totalPrice");
                cart = new Cart(cartId, cus, totalPrice);
                cart.setCartItems(ciDao.selectCartItemsByCartId(cartId));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }
    public Cart selectById(int id, int customer_id) {
        Cart cart = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from carts where id = ? and customer_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, customer_id);
            ResultSet rs = pst.executeQuery();
            CustomerDao cusDao = new CustomerDao();
            while(rs.next()) {
                int cartId = rs.getInt("id");
                Customer cus = cusDao.selectById(rs.getInt("customer_id"));
                double totalPrice = rs.getFloat("totalPrice");
                cart = new Cart(id, cus, totalPrice);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    public static void main(String[] args) {
        CartDao cartDao = new CartDao();
        System.out.println(cartDao.selectByCustomerId(1).getCartItems().size()
        );
    }
}
