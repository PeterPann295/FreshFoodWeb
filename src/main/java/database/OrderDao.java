package database;

import model.Order;
import model.OrderItem;
import utils.JDBCUtil;
import utils.OrderSummary;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao extends AbsDao<Order>{
    private CustomerDao customerDao = new CustomerDao();
    private PaymentMethodDao paymentMethodDao = new PaymentMethodDao();
    private OrderStatusDao orderStatusDao = new OrderStatusDao();
    private VoucherDao voucherDao = new VoucherDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    @Override
    public int insert(Order order) {
        int generatedId = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO orders (customer_id,to_name, total, time_order,numberPhone, delivery_fee, expected_delivery_time, from_address, delivery_address, note, payment_method_id, status_id, voucher_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, order.getCustomer().getId());
            pst.setString(2, order.getToName());
            pst.setDouble(3, order.getTotal());
            pst.setTimestamp(4, order.getDate());
            pst.setString(5, order.getNumberPhone());
            pst.setDouble(6, order.getDeliveryFee());
            pst.setTimestamp(7, order.getDeliveryDate());
            pst.setNString(8, order.getFrom_address());
            pst.setNString(9, order.getTo_address());
            pst.setNString(10, order.getNote());
            pst.setInt(11, order.getPaymentMethod().getId());
            pst.setInt(12, order.getStatus().getId());
            pst.setInt(13, order.getVoucher().getId());
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        order.setId(generatedId); // Cập nhật ID vào đối tượng Order
                    }
                }
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }
    @Override
    public int update(Order order) {
        int rowsAffected = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE orders SET customer_id=?, to_name=?, total=?, time_order=?, numberPhone=?, delivery_fee=?, expected_delivery_time=?, from_address=?, delivery_address=?, note=?, payment_method_id=?, status_id=?, voucher_id=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, order.getCustomer().getId());
            pst.setString(2, order.getToName());
            pst.setDouble(3, order.getTotal());
            pst.setTimestamp(4, order.getDate());
            pst.setString(5, order.getNumberPhone());
            pst.setDouble(6, order.getDeliveryFee());
            pst.setTimestamp(7, order.getDeliveryDate());
            pst.setNString(8, order.getFrom_address());
            pst.setNString(9, order.getTo_address());
            pst.setNString(10, order.getNote());
            pst.setInt(11, order.getPaymentMethod().getId());
            pst.setInt(12, order.getStatus().getId());
            pst.setInt(13, order.getVoucher().getId());
            pst.setInt(14, order.getId()); // Đặt tham số ID của Order cần cập nhật
            rowsAffected = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }


    @Override
    public ArrayList<Order> selectAll() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from orders";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomer(customerDao.selectById(rs.getInt("customer_id")));
                order.setToName(rs.getString("to_name"));
                order.setTotal(rs.getDouble("total"));
                order.setDate(rs.getTimestamp("time_order"));
                order.setNumberPhone(rs.getString("numberPhone"));
                order.setFrom_address(rs.getString("from_address"));
                order.setTo_address(rs.getString("delivery_address"));
                order.setDeliveryFee(rs.getDouble("delivery_fee"));
                order.setDeliveryDate(rs.getTimestamp("expected_delivery_time"));
                order.setNote(rs.getString("note"));
                order.setPaymentMethod(paymentMethodDao.selectById(rs.getInt("payment_method_id")));
                order.setStatus(orderStatusDao.selectById(rs.getInt("status_id")));
                order.setVoucher(voucherDao.selectById(rs.getInt("voucher_id")));
                order.setOrderItems(orderItemDao.selectByOrderId(order));
                orders.add(order);
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order selectById(int id) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from orders where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomer(customerDao.selectById(rs.getInt("customer_id")));
                order.setToName(rs.getString("to_name"));
                order.setTotal(rs.getDouble("total"));
                order.setDate(rs.getTimestamp("time_order"));
                order.setNumberPhone(rs.getString("numberPhone"));
                order.setFrom_address(rs.getString("from_address"));
                order.setTo_address(rs.getString("delivery_address"));
                order.setDeliveryFee(rs.getDouble("delivery_fee"));
                order.setDeliveryDate(rs.getTimestamp("expected_delivery_time"));
                order.setNote(rs.getString("note"));
                order.setPaymentMethod(paymentMethodDao.selectById(rs.getInt("payment_method_id")));
                order.setStatus(orderStatusDao.selectById(rs.getInt("status_id")));
                order.setVoucher(voucherDao.selectById(rs.getInt("voucher_id")));
                order.setOrderItems(orderItemDao.selectByOrderId(order));
                orders.add(order);
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return orders.get(0);
    }
    public ArrayList<Order> selectByCustomerId(int customerId) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from orders where customer_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomer(customerDao.selectById(rs.getInt("customer_id")));
                order.setToName(rs.getString("to_name"));
                order.setTotal(rs.getDouble("total"));
                order.setDate(rs.getTimestamp("time_order"));
                order.setNumberPhone(rs.getString("numberPhone"));
                order.setFrom_address(rs.getString("from_address"));
                order.setTo_address(rs.getString("delivery_address"));
                order.setDeliveryFee(rs.getDouble("delivery_fee"));
                order.setDeliveryDate(rs.getTimestamp("expected_delivery_time"));
                order.setNote(rs.getString("note"));
                order.setPaymentMethod(paymentMethodDao.selectById(rs.getInt("payment_method_id")));
                order.setStatus(orderStatusDao.selectById(rs.getInt("status_id")));
                order.setVoucher(voucherDao.selectById(rs.getInt("voucher_id")));
                order.setOrderItems(orderItemDao.selectByOrderId(order));
                orders.add(order);
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public ArrayList<Order> selectByStatusId(int statusId) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from orders where status_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, statusId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomer(customerDao.selectById(rs.getInt("customer_id")));
                order.setToName(rs.getString("to_name"));
                order.setTotal(rs.getDouble("total"));
                order.setDate(rs.getTimestamp("time_order"));
                order.setNumberPhone(rs.getString("numberPhone"));
                order.setFrom_address(rs.getString("from_address"));
                order.setTo_address(rs.getString("delivery_address"));
                order.setDeliveryFee(rs.getDouble("delivery_fee"));
                order.setDeliveryDate(rs.getTimestamp("expected_delivery_time"));
                order.setNote(rs.getString("note"));
                order.setPaymentMethod(paymentMethodDao.selectById(rs.getInt("payment_method_id")));
                order.setStatus(orderStatusDao.selectById(rs.getInt("status_id")));
                order.setVoucher(voucherDao.selectById(rs.getInt("voucher_id")));
                order.setOrderItems(orderItemDao.selectByOrderId(order));
                orders.add(order);
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public ArrayList<Order> selectByCustomerIdAndStatusId(int customerId, int statusId) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from orders where customer_id = ? and status_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, customerId);
            pst.setInt(2, statusId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomer(customerDao.selectById(rs.getInt("customer_id")));
                order.setToName(rs.getString("to_name"));
                order.setTotal(rs.getDouble("total"));
                order.setDate(rs.getTimestamp("time_order"));
                order.setNumberPhone(rs.getString("numberPhone"));
                order.setFrom_address(rs.getString("from_address"));
                order.setTo_address(rs.getString("delivery_address"));
                order.setDeliveryFee(rs.getDouble("delivery_fee"));
                order.setDeliveryDate(rs.getTimestamp("expected_delivery_time"));
                order.setNote(rs.getString("note"));
                order.setPaymentMethod(paymentMethodDao.selectById(rs.getInt("payment_method_id")));
                order.setStatus(orderStatusDao.selectById(rs.getInt("status_id")));
                order.setVoucher(voucherDao.selectById(rs.getInt("voucher_id")));
                order.setOrderItems(orderItemDao.selectByOrderId(order));
                orders.add(order);
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public int selectTotalProductSold(int productId){
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT SUM(oi.quantity) AS total_quantity_sold\n" +
                    "FROM order_items oi\n" +
                    "JOIN orders o ON oi.order_id = o.id\n" +
                    "WHERE oi.product_id = ? AND o.status_id = ?;\n";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, productId);
            pst.setInt(2, 4);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getInt("total_quantity_sold");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public ArrayList<OrderSummary> getTotalRevenue7Days(){
        ArrayList<OrderSummary> orders = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT DATE(time_order) AS order_date, SUM(total) AS total_amount " +
                    "FROM orders " +
                    "WHERE status_id = 4 " +
                    "GROUP BY DATE(time_order)";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Date orderDate = rs.getDate("order_date");
                double totalAmount = rs.getDouble("total_amount");
                orders.add(new OrderSummary(orderDate, totalAmount));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        System.out.println(orderDao.getTotalRevenue7Days().size());
    }
}
