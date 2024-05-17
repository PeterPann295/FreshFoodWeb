package database;

import model.CartItem;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartItemDao extends AbsDao<CartItem> {

    @Override
    public int insert(CartItem cartItem) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO CartItems(cart_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cartItem.getCart().getId());
            pst.setDouble(2, cartItem.getProduct().getId());
            pst.setInt(3, cartItem.getQuantity());
            int i = pst.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(CartItem cartItem) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE CartItems SET quantity = ? WHERE cart_id = ? AND product_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cartItem.getQuantity());
            pst.setInt(2, cartItem.getCart().getId());
            pst.setInt(3, cartItem.getProduct().getId());
            int i = pst.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(CartItem cartItem) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM CartItems WHERE cart_id = ? AND product_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cartItem.getCart().getId());
            pst.setInt(2, cartItem.getProduct().getId());
            int i = pst.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    private List<CartItem> selectCartItemsByCartId(int cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM CartItems WHERE cart_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cartId);
            ResultSet rs = pst.executeQuery();
            CartDao cartDao = new CartDao();
            ProductDao productDao = new ProductDao();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCart(cartDao.selectById(rs.getInt("cart_id"), rs.getInt("customer_id")));
                item.setProduct(productDao.selectById(rs.getInt("product_id")));
                item.setQuantity(rs.getInt("quantity"));
                cartItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }
}