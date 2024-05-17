package controller;

import database.CartDao;
import database.CartItemDao;
import database.CustomerDao;
import database.ProductDao;
import model.*;
import utils.Encode;
import utils.GoogleAccount;
import utils.GoogleLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.HttpJspPage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/customer")
public class CustomerSerlvet extends HttpServlet {
    private CustomerDao cusDao = new CustomerDao();
    private CartDao cartDao = new CartDao();
    private CartItemDao cartItemDao = new CartItemDao();
    private ProductDao prodDao = new ProductDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if(action.equals("loginGoogle")){
            loginGoogle(req,resp);
        }else if(action.equals("loginFacebook")) {
            loginFacebook(req, resp);
        } else if(action.equals("login")){
            login(req, resp);
        } else if(action.equals("register")){
             register(req, resp);
        } else if (action.equals("addToCart")) {
            addToCart(req, resp);
        }else if(action.equals("selectProductOnCart")){
            selectProductOnCart(req, resp);
        } else if (action.equals("goConfirmAddress")) {
            goConfirmAddress(req, resp);
        } else if(action.equals("confirmAddress")) {
            confirmAddress(req, resp);
        }
    }




    private void loginGoogle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount account = gg.getUserInfo(accessToken);
        Customer customer = cusDao.checkProviderUserID(account.getId());
        if(customer == null) {
            customer = new Customer();
            customer.setProvider_user_id(account.getId());
            customer.setFullName(account.getName());
            customer.setEmail(account.getEmail());
            customer.setProvider("google");
            cusDao.insert(customer);
        }
        customer = cusDao.checkProviderUserID(account.getId());
        HttpSession session = req.getSession();
        session.setAttribute("customer_login", customer);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
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
        customer = cusDao.checkProviderUserID(id);
        HttpSession session = req.getSession();
        session.setAttribute("customer_login", customer);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Customer customer = cusDao.checkLogin(username, Encode.toSHA1(password));
        String url = "";
        if(customer != null){
            HttpSession session = req.getSession();
            session.setAttribute("customer_login", customer);
            url = "/index.jsp" ;
        }else {
            req.setAttribute("error_login", "Tên đăng nhập hoặc mật khẩu không đúng!");
            url = "/dangNhap.jsp";
        }
        req.getRequestDispatcher(url).forward(req,resp);
    }
    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");
        String fullName = req.getParameter("full-name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        username = (username.equals("null")) ? "" : username;
        password = (password.equals("null")) ? "" : password;
        fullName = (fullName.equals("null")) ? "" : fullName;
        phone = (phone.equals("null")) ? "" : phone;
        email = (email.equals("null")) ? "" : email;
        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.setAttribute("confirm-password", confirmPassword);
        req.setAttribute("fullName", fullName);
        req.setAttribute("phone", phone);
        req.setAttribute("email", email);
        boolean error = false ;
        String url = "";
        if(cusDao.checkUsername(username)){
            req.setAttribute("err_username", "Tên Đăng Nhập Đã Tồn Tại, Vui Lòng Chọn Tên Đăng Nhập Khác");
            url = "/dangKi.jsp";
            error = true;
        }
        if (!password.equals(confirmPassword)) {
            req.setAttribute("err_password", "Nhập Mật Khẩu Không Khớp");
            url = "/dangKi.jsp";
            error = true;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);

        if (!emailMatcher.matches()) {
            error = true;
            url = "/dangKi.jsp";
            req.setAttribute("err_email", "Câu trúc của email chưa đúng!");
        }

        // Kiểm tra số diện thoại
        Pattern soDienThoaiPattern = Pattern.compile("\\d{10}");
        Matcher soDienThoaiMatcher = soDienThoaiPattern.matcher(phone);
        if (!soDienThoaiMatcher.matches()) {
            error = true;
            url = "/dangKi.jsp";
            req.setAttribute("err_phone", "Số điện thoại bao gồm 10 ký tự!");
        }
        if(username.length() < 8) {
            error = true;
            url = "/dangKi.jsp";
            req.setAttribute("err_username", "Tên đăng nhập phải từ 6 kí tự");
        }
        if(password.length() < 8) {
            error = true;
            url = "/dangKi.jsp";
            req.setAttribute("err_password", "Mật khẩu tối thiểu 6 kí tự");
        }
        if (!error) {
            Customer cus = new Customer(username, Encode.toSHA1(password), fullName,email, phone);
            cusDao.insert(cus);
            req.setAttribute("register_success", "Chức mừng bạn đăng kí thành công, vui lòng đăng nhập");
            url = "/dangNhap.jsp";
        }
        req.getRequestDispatcher(url).forward(req,resp);
    }
    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer_login");
        Cart cart = cartDao.selectByCustomerId(customer.getId());
        if(cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cart.setTotalPrice(0);
            cartDao.insert(cart);
        }
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = prodDao.selectById(productId);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setCart(cart);
        cartItemDao.insert(cartItem);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/customer/gioHang.jsp");

    }
    private void selectProductOnCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String productId = req.getParameter("productId");
        int id = Integer.parseInt(productId);
        boolean isChecked = Boolean.parseBoolean(req.getParameter("isChecked"));
        // Kiểm tra xem prices có tồn tại không
        HttpSession session = req.getSession();
        Double prices = (Double) session.getAttribute("prices");
        System.out.println(prices);
        if (prices == null) {
            prices = (double) 0;
        }

        // Nếu isChecked là true, thêm giá sản phẩm vào prices, ngược lại, trừ giá sản phẩm ra khỏi prices
        if (isChecked) {
            // Lấy giá sản phẩm từ cơ sở dữ liệu hoặc các nguồn khác và thêm vào prices
            double productPrice = prodDao.selectById(id).getFinalPrice(); // Hàm này cần phải được triển khai
            prices += productPrice;
        } else {
            // Lấy giá sản phẩm từ cơ sở dữ liệu hoặc các nguồn khác và trừ ra khỏi prices
            double productPrice = prodDao.selectById(id).getFinalPrice(); // Hàm này cần phải được triển khai
            prices -= productPrice;
        }
        session.setAttribute("prices", prices);
        resp.getWriter().write(String.valueOf(prices));
    }
    private void goConfirmAddress(HttpServletRequest req, HttpServletResponse resp) {
        String[] selectedProductIds = req.getParameterValues("selectedProducts");

        if (selectedProductIds != null) {
            ArrayList<Product> products = new ArrayList<Product>();
            for (String productId : selectedProductIds) {
                products.add(prodDao.selectById(Integer.parseInt(productId)));
            }
            double totalPrice = 0;
            double totalWeight = 0;
            for (Product product : products) {
                totalPrice += product.getFinalPrice();
                totalWeight += product.getWeight();
            }
            HttpSession session = req.getSession();
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("totalWeight", totalWeight);
            session.setAttribute("selectedProducts", products);
        } else {
            System.out.println("No products selected.");
        }

    }
    private void confirmAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer_login");
        String name = req.getParameter("to_customer");
        String numberPhone = req.getParameter("phone");
        String[] provinceId = req.getParameterValues("provinceId");
        String[] districtId = req.getParameterValues("districtId");
        String[] wardId = req.getParameterValues("wardId");
        String addressDetail = req.getParameter("address_detail");
        Address address = new Address(customer, name, numberPhone,Integer.parseInt(provinceId[0]), Integer.parseInt(districtId[0]), Integer.parseInt(wardId[0]), addressDetail);
        System.out.println(address.toString());

    }
}
