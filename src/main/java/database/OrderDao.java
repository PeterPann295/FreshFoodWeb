package database;

import model.Order;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderDao extends AbsDao<Order>{
    @Override
    public int insert(Order order) {
        int generatedId = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO orders (customer_id, total, time_order, delivery_fee, expected_delivery_time, from_address, delivery_address, note, payment_method_id, status_id, voucher_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, order.getCustomer().getId());
            pst.setDouble(2, order.getTotal());
            pst.setTimestamp(3, order.getDate());
            pst.setDouble(4, order.getDeliveryFee());
            pst.setTimestamp(5, order.getDeliveryDate());
            pst.setNString(6, order.getFrom_address());
            pst.setNString(7, order.getTo_address());
            pst.setNString(8, order.getNote());
            pst.setInt(9, order.getPaymentMethod().getId());
            pst.setInt(10, order.getStatus().getId());
            pst.setInt(11, order.getVoucher().getId());
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        order.setId(generatedId); // Cập nhật ID vào đối tượng Order
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}
