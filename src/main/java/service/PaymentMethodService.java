package service;

import database.PaymentMethodDao;
import model.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodService {
    private PaymentMethodDao paymentMethodDao;

    public PaymentMethodService() {
        this.paymentMethodDao = new PaymentMethodDao();
    }

    public int insert(PaymentMethod paymentMethod) {
        return paymentMethodDao.insert(paymentMethod);
    }

    public int update(PaymentMethod paymentMethod) {
        return paymentMethodDao.update(paymentMethod);
    }

    public int delete(PaymentMethod paymentMethod) {
        return paymentMethodDao.delete(paymentMethod);
    }

    public ArrayList<PaymentMethod> selectAll() {
        return paymentMethodDao.selectAll();
    }

    public PaymentMethod selectById(int id) {
        return paymentMethodDao.selectById(id);
    }

}
