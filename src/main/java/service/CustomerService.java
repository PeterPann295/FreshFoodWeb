package service;

import database.CustomerDao;
import model.Customer;

import java.util.ArrayList;

public class CustomerService {
    private CustomerDao customerDao;

    public CustomerService() {
        this.customerDao = new CustomerDao();
    }

    public int insert(Customer customer) {
        if (customerDao.checkUsername(customer.getUsername())) {
            System.out.println("Username already exists!");
            return 0;
        }
        return customerDao.insert(customer);
    }

    public int update(Customer customer) {
        return customerDao.update(customer);
    }

    public int delete(Customer customer) {
        return customerDao.delete(customer);
    }

    public ArrayList<Customer> selectAll() {
        return customerDao.selectAll();
    }

    public Customer selectById(int id) {
        return customerDao.selectById(id);
    }

    public boolean checkUsername(String username) {
        return customerDao.checkUsername(username);
    }

    public Customer checkProviderUserID(String providerUserId) {
        return customerDao.checkProviderUserID(providerUserId);
    }

    public Customer checkLogin(String username, String password) {
        return customerDao.checkLogin(username, password);
    }
}
