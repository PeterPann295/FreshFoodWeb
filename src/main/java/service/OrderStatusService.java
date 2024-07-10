package service;

import database.OrderStatusDao;
import model.OrderStatus;

import java.util.ArrayList;

public class OrderStatusService {
    private OrderStatusDao orderStatusDao;

    public OrderStatusService() {
        this.orderStatusDao = new OrderStatusDao();
    }

    public int insert(OrderStatus orderStatus) {
        return orderStatusDao.insert(orderStatus);
    }

    public int update(OrderStatus orderStatus) {
        return orderStatusDao.update(orderStatus);
    }

    public int delete(OrderStatus orderStatus) {
        return orderStatusDao.delete(orderStatus);
    }

    public ArrayList<OrderStatus> selectAll() {
        return orderStatusDao.selectAll();
    }

    public OrderStatus selectById(int id) {
        return orderStatusDao.selectById(id);
    }

}
