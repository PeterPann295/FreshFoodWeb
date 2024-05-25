package database;

import model.Order;
import model.OrderItem;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderItemDao implements IDao<OrderItem>{

    @Override
    public int insert(OrderItem orderItem) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "insert into order_items(order_id, product_id, quantity) values (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderItem.getOrder().getId());
            pst.setInt(2, orderItem.getProduct().getId());
            pst.setInt(3, orderItem.getQuantity());
            int i = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(OrderItem orderItem) {
        return 0;
    }

    @Override
    public int delete(OrderItem orderItem) {
        return 0;
    }

    @Override
    public ArrayList<OrderItem> selectAll() {
        return null;
    }

    @Override
    public OrderItem selectById(int id) {
        return null;
    }
    public ArrayList<OrderItem> selectByOrderId(Order order) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from order_items where order_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, order.getId());
            ResultSet rs = pst.executeQuery();
            ProductDao productDao = new ProductDao();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setId(rs.getInt("id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setProduct(productDao.selectById(rs.getInt("product_id")));
                orderItems.add(orderItem);
            }
            JDBCUtil.closeConnection(con);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public static void main(String[] args) {

    }

}
