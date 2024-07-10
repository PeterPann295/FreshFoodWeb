package service;

import database.OrderDao;
import model.Order;
import utils.OrderSummary;

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

    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        // Test insert order
        Order newOrder = new Order();
        // Set properties for newOrder
        int result = orderService.insert(newOrder);
        System.out.println("Insert Order Result: " + result);

        // Test get all orders
        ArrayList<Order> orders = orderService.selectAll();
        System.out.println("All Orders: " + orders);

        // Test get order by id
        Order order = orderService.selectById(1);
        System.out.println("Order with ID 1: " + order);

        // Test get orders by customer id
        ArrayList<Order> customerOrders = orderService.selectByCustomerId(1);
        System.out.println("Orders by Customer ID 1: " + customerOrders);

        // Test get orders by status id
        ArrayList<Order> statusOrders = orderService.selectByStatusId(1);
        System.out.println("Orders by Status ID 1: " + statusOrders);

        // Test get orders by customer id and status id
        ArrayList<Order> customerStatusOrders = orderService.selectByCustomerIdAndStatusId(1, 1);
        System.out.println("Orders by Customer ID 1 and Status ID 1: " + customerStatusOrders);

        // Test get total product sold by product id
        int totalSold = orderService.selectTotalProductSold(1);
        System.out.println("Total Product Sold by Product ID 1: " + totalSold);

        // Test get total revenue for the last 7 days
        ArrayList<OrderSummary> totalRevenue = orderService.getTotalRevenue7Days();
        System.out.println("Total Revenue for Last 7 Days: " + totalRevenue);
    }
}
