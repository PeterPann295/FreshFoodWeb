package service;

import database.DiscountDao;
import model.Discount;

import java.util.ArrayList;

public class DiscountService {
    private DiscountDao discountDao;

    public DiscountService() {
        this.discountDao = new DiscountDao();
    }

    public int insert(Discount discount) {
        return discountDao.insert(discount);
    }
    public int delete(Discount discount) {
        return discountDao.delete(discount);
    }
    public int update(Discount discount) {
        return discountDao.update(discount);
    }
        public ArrayList<Discount> selectAll() {
        return discountDao.selectAll();
    }

    public Discount selectById(int id) {
        return discountDao.selectById(id);
    }

    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();

        // Test insert discount
        Discount newDiscount = new Discount();
        newDiscount.setName("New Year Sale");
        newDiscount.setPercent(20);
        int result = discountService.insert(newDiscount);
        System.out.println("Insert Discount Result: " + result);

        // Test get all discounts
        ArrayList<Discount> discounts = discountService.selectAll();
        System.out.println("All Discounts: " + discounts);

        // Test get discount by id
        Discount discount = discountService.selectById(1);
        System.out.println("Discount with ID 1: " + discount);
    }
}
