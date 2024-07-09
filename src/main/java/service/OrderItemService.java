package service;

import database.OrderItemDao;
import model.Order;
import model.OrderItem;

import java.util.ArrayList;

public class OrderItemService {
    private OrderItemDao orderItemDao;

    public OrderItemService() {
        this.orderItemDao = new OrderItemDao();
    }

    public int insert(OrderItem orderItem) {
        return orderItemDao.insert(orderItem);
    }

    public int update(OrderItem orderItem) {
        return orderItemDao.update(orderItem);
    }

    public int delete(OrderItem orderItem) {
        return orderItemDao.delete(orderItem);
    }

    public ArrayList<OrderItem> selectAll() {
        return orderItemDao.selectAll();
    }

    public OrderItem selectById(int id) {
        return orderItemDao.selectById(id);
    }

    public ArrayList<OrderItem> selectByOrderId(Order order) {
        return orderItemDao.selectByOrderId(order);
    }

    public static void main(String[] args) {
        OrderItemService orderItemService = new OrderItemService();

        // Test insert order item
        OrderItem newOrderItem = new OrderItem();
        // Set properties for newOrderItem
        int result = orderItemService.insert(newOrderItem);
        System.out.println("Insert OrderItem Result: " + result);

        // Test get all order items
        ArrayList<OrderItem> orderItems = orderItemService.selectAll();
        System.out.println("All OrderItems: " + orderItems);

        // Test get order item by id
        OrderItem orderItem = orderItemService.selectById(1);
        System.out.println("OrderItem with ID 1: " + orderItem);

        // Test get order items by order id
        Order order = new Order();
        order.setId(1); // Set order ID
        ArrayList<OrderItem> orderItemsByOrder = orderItemService.selectByOrderId(order);
        System.out.println("OrderItems by Order ID 1: " + orderItemsByOrder);
    }
}
