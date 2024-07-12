package database;

import model.Order;
import utils.JDBCUtil;
import utils.OrderSummaryYear;
import utils.OrderSummary;
import utils.OrderTrend;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

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
            if(order.getVoucher() !=null){
                pst.setInt(13, order.getVoucher().getId());
            }else{
                pst.setNull(13, java.sql.Types.INTEGER);
            }
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        order.setId(generatedId); // Cập nhật ID vào đối tượng Order
                    }
                }
                super.insert(order);
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
            super.update(order);
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
                order.setBeforeData(order.toString());
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
                order.setBeforeData(order.toString());
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
                order.setBeforeData(order.toString());
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
                order.setBeforeData(order.toString());
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
                    "GROUP BY DATE(time_order) ORDER BY \n" +
                    "    order_date DESC limit 7";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Date orderDate = rs.getDate("order_date");
                double totalAmount = rs.getDouble("total_amount");
                orders.add(new OrderSummary(orderDate, totalAmount));
            }
            Collections.reverse(orders);
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public double totalRevenue(){
        double result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT \n" +
                    "    SUM(total) AS total_revenue\n" +
                    "FROM \n" +
                    "    orders\n" +
                    "WHERE \n" +
                    "    status_id = 4";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                result = rs.getDouble("total_revenue");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public ArrayList<OrderSummaryYear> getTotalRevenueEveryYear(){
        ArrayList<OrderSummaryYear> orders = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT \n" +
                    "    YEAR(time_order) AS order_year,\n" +
                    "    SUM(total) AS total_revenue\n" +
                    "FROM \n" +
                    "    orders\n" +
                    "WHERE \n" +
                    "    status_id = 4\n" +
                    "GROUP BY \n" +
                    "    YEAR(time_order)\n" +
                    "ORDER BY \n" +
                    "    order_year ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int year = rs.getInt("order_year");
                double totalAmount = rs.getDouble("total_revenue");
                orders.add(new OrderSummaryYear(year, totalAmount));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public ArrayList<OrderTrend> selectOrderTrend() {
        ArrayList<OrderTrend> orders = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT\n" +
                    "    c.id AS customer_id,\n" +
                    "    c.fullName AS customer_name,\n" +
                    "    SUM(CASE WHEN MONTH(o.time_order) = MONTH(CURRENT_DATE) THEN 1 ELSE 0 END) AS orders_this_month,\n" +
                    "    SUM(CASE WHEN MONTH(o.time_order) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) THEN 1 ELSE 0 END) AS orders_last_month,\n" +
                    "    SUM(CASE WHEN MONTH(o.time_order) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 2 MONTH)) THEN 1 ELSE 0 END) AS orders_two_months_ago,\n" +
                    "    SUM(CASE WHEN MONTH(o.time_order) = MONTH(CURRENT_DATE) THEN o.total ELSE 0 END) AS total_this_month,\n" +
                    "    SUM(CASE WHEN MONTH(o.time_order) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) THEN o.total ELSE 0 END) AS total_last_month,\n" +
                    "    SUM(CASE WHEN MONTH(o.time_order) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 2 MONTH)) THEN o.total ELSE 0 END) AS total_two_months_ago,\n" +
                    "    SUM(o.total) AS total_order_amount\n" +
                    "FROM\n" +
                    "    customers c\n" +
                    "    LEFT JOIN orders o ON c.id = o.customer_id\n" +
                    "WHERE\n" +
                    "    o.time_order >= DATE_SUB(CURRENT_DATE, INTERVAL 3 MONTH)\n" +
                    "GROUP BY\n" +
                    "    c.id, c.fullName\n" +
                    "ORDER BY\n" +
                    "    total_order_amount DESC;";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            CustomerDao customerDao = new CustomerDao();
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                int qm1 = rs.getInt("orders_this_month");
                int qm2 = rs.getInt("orders_last_month");
                int qm3 = rs.getInt("orders_two_months_ago");
                double totalM1 = rs.getDouble("total_this_month");
                double totalM2 = rs.getDouble("total_last_month");
                double totalM3 = rs.getDouble("total_two_months_ago");
                double totalAmount = rs.getDouble("total_order_amount");
                OrderTrend orderTrend = new OrderTrend(customerDao.selectById(customerId), qm1, qm2, qm3, totalM1, totalM2, totalM3, totalAmount);
                orders.add(orderTrend);
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        System.out.println(orderDao.selectOrderTrend().size());
    }
}
