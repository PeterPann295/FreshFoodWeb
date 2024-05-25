package database;

import model.OrderStatus;
import model.PaymentMethod;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderStatusDao implements IDao<OrderStatus>{

    @Override
    public int insert(OrderStatus orderStatus) {
        return 0;
    }

    @Override
    public int update(OrderStatus orderStatus) {
        return 0;
    }

    @Override
    public int delete(OrderStatus orderStatus) {
        return 0;
    }

    @Override
    public ArrayList<OrderStatus> selectAll() {
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from order_status";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int id_pm = rs.getInt("id");
                String name = rs.getNString("name");
                orderStatuses.add(new OrderStatus(id_pm, name));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }

    @Override
    public OrderStatus selectById(int id) {
        OrderStatus orderStatus = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from order_status where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int id_pm = rs.getInt("id");
                String name = rs.getNString("name");
                orderStatus = new OrderStatus(id_pm, name);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderStatus;
    }

    public static void main(String[] args) {
        OrderStatusDao dao = new OrderStatusDao();
        System.out.println(dao.selectAll().size());
    }
}
