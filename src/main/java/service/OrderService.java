package service;

import database.OrderDao;
import model.Order;
import utils.OrderSummary;
import utils.OrderSummaryYear;
import utils.OrderTrend;

import java.util.ArrayList;

public class OrderService {
    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public int insert(Order order) {
        return orderDao.insert(order);
    }

    public int update(Order order) {
        return orderDao.update(order);
    }

    public int delete(Order order) {
        return orderDao.delete(order);
    }

    public ArrayList<Order> selectAll() {
        return orderDao.selectAll();
    }

    public Order selectById(int id) {
        return orderDao.selectById(id);
    }

    public ArrayList<Order> selectByCustomerId(int customerId) {
        return orderDao.selectByCustomerId(customerId);
    }

    public ArrayList<Order> selectByStatusId(int statusId) {
        return orderDao.selectByStatusId(statusId);
    }

    public ArrayList<Order> selectByCustomerIdAndStatusId(int customerId, int statusId) {
        return orderDao.selectByCustomerIdAndStatusId(customerId, statusId);
    }

    public int selectTotalProductSold(int productId) {
        return orderDao.selectTotalProductSold(productId);
    }

    public ArrayList<OrderSummary> getTotalRevenue7Days() {
        return orderDao.getTotalRevenue7Days();
    }

    public double totalRevenue() {
        return orderDao.totalRevenue();
    }
    public ArrayList<OrderSummaryYear> getTotalRevenueEveryYear() {
        return orderDao.getTotalRevenueEveryYear();
    }
    public ArrayList<OrderTrend> selectOrderTrend() {
        return orderDao.selectOrderTrend();
    }
}
