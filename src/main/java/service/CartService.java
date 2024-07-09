package service;

import database.CartDao;
import model.Cart;

public class CartService {
    private CartDao cartDao;

    public CartService() {
        cartDao = new CartDao();
    }

    public int insert(Cart cart) {
        return cartDao.insert(cart);
    }

    public int update(Cart cart) {
        return cartDao.update(cart);
    }

    public Cart selectById(int id) {
        return cartDao.selectById(id);
    }
    public Cart selectByCustomerId(int customer_id) {
        return cartDao.selectByCustomerId(customer_id);
    }
    public Cart selectById(int id, int customer_id) {
        return cartDao.selectById(id, customer_id);
    }
}