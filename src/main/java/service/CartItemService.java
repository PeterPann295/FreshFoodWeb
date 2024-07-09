package service;

import database.CartItemDao;
import model.CartItem;

import java.util.List;

public class CartItemService {
    CartItemDao cartItemDao;

    public CartItemService() {
        this.cartItemDao = new CartItemDao();
    }

    public int insert(CartItem cartItem) {
        return cartItemDao.insert(cartItem);
    }

    public int update(CartItem cartItem) {
        return cartItemDao.update(cartItem);
    }

    public int delete(CartItem cartItem) {
        return cartItemDao.delete(cartItem);
    }

    public CartItem selectById(int id) {
        return cartItemDao.selectById(id);
    }
    public List<CartItem> selectCartItemsByCartId(int cartId) {
        return cartItemDao.selectCartItemsByCartId(cartId);
    }
    public CartItem selectCartItemsByCartIdAndProductId(int cartId, int product_id) {
        return cartItemDao.selectCartItemsByCartIdAndProductId(cartId, product_id);
    }
    }
