package controller;

import database.CustomerDao;
import model.Customer;
import utils.GoogleAccount;
import utils.GoogleLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/customer")
public class CustomerSerlvet extends HttpServlet {
    private CustomerDao cusDao = new CustomerDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("loginGoogle")){
            loginGoogle(req,resp);
        }else if(action.equals("loginFacebook")) {
            loginFacebook(req, resp);
        } else if(action.equals("login")){

        }
    }
    private void loginGoogle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount account = gg.getUserInfo(accessToken);
    }
    private void loginFacebook (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String id =req.getParameter("id");
        String email = req.getParameter("email");
        Customer customer = cusDao.checkProviderUserID(id);
        if(customer == null) {
            customer = new Customer();
            customer.setFullName(name);
            customer.setEmail(email);
            customer.setProvider("facebook");
            customer.setProvider_user_id(id);
            cusDao.insert(customer);
        }
        HttpSession session = req.getSession();
        session.setAttribute("customer_login", customer);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
