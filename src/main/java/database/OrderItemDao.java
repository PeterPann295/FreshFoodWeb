package database;

import model.OrderItem;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
