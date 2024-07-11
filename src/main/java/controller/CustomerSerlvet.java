package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.*;
import model.*;
import org.apache.http.client.fluent.Response;
import utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.HttpJspPage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/customer")
public class CustomerSerlvet extends HttpServlet {
    private CustomerDao cusDao = new CustomerDao();
    private CartDao cartDao = new CartDao();
    private CartItemDao cartItemDao = new CartItemDao();
    private ProductDao prodDao = new ProductDao();
    private VoucherDao voucherDao = new VoucherDao();
    private OrderStatusDao orderStatusDao = new OrderStatusDao();
    private PaymentMethodDao paymentMethodDao = new PaymentMethodDao();
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    private ImportProductDao importProductDao = new ImportProductDao();

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
        if (action.equals("loginGoogle")) {
            loginGoogle(req, resp);
        } else if (action.equals("loginFacebook")) {
            loginFacebook(req, resp);
        } else if (action.equals("login")) {
            login(req, resp);
        } else if (action.equals("logout")) {
            logout(req, resp);
        } else if (action.equals("register")) {
            register(req, resp);
        } else if (action.equals("addToCart")) {
            addToCart(req, resp);
        } else if (action.equals("checkLoginCustomer")) {
            checkLoginCustomer(req, resp);
        } else if (action.equals("selectProductOnCart")) {
            selectProductOnCart(req, resp);
        } else if (action.equals("selectAllProductsOnCart")) {
            selectAllProductsOnCart(req, resp);
        } else if (action.equals("updateQuantityOnCart")) {
            updateQuantityOnCart(req, resp);
        } else if (action.equals("removeCartItem")) {
            removeCartItem(req, resp);
        } else if (action.equals("goConfirmAddress")) {
            goConfirmAddress(req, resp);
        } else if (action.equals("confirmAddress")) {
            confirmAddress(req, resp);
        } else if (action.equals("confirmVoucher")) {
            confirmVoucher(req, resp);
        } else if (action.equals("order")) {
            order(req, resp);
        } else if (action.equals("confirmBank")) {
            confirmBanK(req, resp);
        } else if (action.equals("getOrderStatus")) {
            getOrderStatus(req, resp);
        } else if (action.equals("updateOrder")) {
            updateOrder(req, resp);
        } else if (action.equals("detailOrder")) {
            detailOrder(req, resp);
        } else if (action.equals("productDetail")) {
            productDetail(req, resp);
        } else if (action.equals("filterProduct")) {
            filterProduct(req, resp);
        } else if (action.equals("searchByNameProduct")) {
            searchByNameProduct(req, resp);
        } else if (action.equals("searchByParentCategory")) {
            searchByParentCategory(req, resp);
        } else if(action.equals("changePassword")) {
            changePassword(req, resp);
        } else if(action.equals("changeInformation")){
            changeInformation(req,resp);
        } else if(action.equals("forgetPassword")){
            forgetPassword(req, resp);
        } else if(action.equals("resetPassword")){
            resetPassword(req, resp);
        } else if (action.equals("goListProduct")) {
            goListProduct(req,resp);
        }
    }


    private void loginGoogle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount account = gg.getUserInfo(accessToken);
        Customer customer = cusDao.checkProviderUserID(account.getId());
        if (customer == null) {
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
        req.getRequestDispatcher("/trangChu.jsp").forward(req, resp);
    }

    private void loginFacebook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String email = req.getParameter("email");
        Customer customer = cusDao.checkProviderUserID(id);
        if (customer == null) {
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
        req.getRequestDispatcher("/trangChu.jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Customer customer = cusDao.checkLogin(username, Encode.toSHA1(password));
        String url = "";
        HttpSession session = req.getSession();
        if (customer != null) {
            session.setAttribute("customer_login", customer);
            if(customer.isRole()){
                url = "/admin/thongKe.jsp";
            }else{
                url = "/trangChu.jsp";
            }
        } else {
            session.setAttribute("error_login", "Tên đăng nhập hoặc mật khẩu không đúng!");
            url = "/dangNhap.jsp";
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + url);

    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/trangChu.jsp");
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
        boolean error = false;
        String url = "";
        if (cusDao.checkUsername(username)) {
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
        if (username.length() < 8) {
            error = true;
            url = "/dangKi.jsp";
            req.setAttribute("err_username", "Tên đăng nhập phải từ 6 kí tự");
        }
        if (password.length() < 8) {
            error = true;
            url = "/dangKi.jsp";
            req.setAttribute("err_password", "Mật khẩu tối thiểu 6 kí tự");
        }
        if (!error) {
            Customer cus = new Customer(username, Encode.toSHA1(password), fullName, phone, email);
            cusDao.insert(cus);
            req.setAttribute("register_success", "Chức mừng bạn đăng kí thành công, vui lòng đăng nhập");
            url = "/dangNhap.jsp";
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        String quantityParam = (req.getParameter("quantity") == null) ? "1" : req.getParameter("quantity");
        int quantity = Integer.parseInt(quantityParam);
        Customer customer = (Customer) session.getAttribute("customer_login");
        Cart cart = cartDao.selectByCustomerId(customer.getId());
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cart.setTotalPrice(0);
            cartDao.insert(cart);
            cart = cartDao.selectByCustomerId(customer.getId());
            System.out.println(cart);
        }
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = prodDao.selectById(productId);

        CartItem cartItem = cartItemDao.selectCartItemsByCartIdAndProductId(cart.getId(), productId);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemDao.update(cartItem);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cartItemDao.insert(cartItem);
        }
        cart = cartDao.selectByCustomerId(customer.getId());
        int cartSize = cart.getTotalQuantity();
        System.out.println(cartSize);
        resp.getWriter().write("{\"cartSize\": " + cartSize + "}");
    }

    private void checkLoginCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        boolean isLoggedIn = (req.getSession().getAttribute("customer_login") != null);
        resp.getWriter().write("{\"isLoggedIn\": " + isLoggedIn + "}");
    }

    private void selectProductOnCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String cartId = req.getParameter("cartId");
        int id = Integer.parseInt(cartId);
        boolean isChecked = Boolean.parseBoolean(req.getParameter("isChecked"));
        // Kiểm tra xem prices có tồn tại không
        HttpSession session = req.getSession();
        Double prices = (Double) session.getAttribute("prices");
        System.out.println(prices);
        if (prices == null) {
            prices = (double) 0;
        }
        CartItem cartItem = cartItemDao.selectById(id);
        // Nếu isChecked là true, thêm giá sản phẩm vào prices, ngược lại, trừ giá sản phẩm ra khỏi prices
        if (isChecked) {
            // Lấy giá sản phẩm từ cơ sở dữ liệu hoặc các nguồn khác và thêm vào prices
            double productPrice = cartItem.getQuantity() * cartItem.getProduct().getFinalPrice(); // Hàm này cần phải được triển khai
            prices += productPrice;
        } else {
            // Lấy giá sản phẩm từ cơ sở dữ liệu hoặc các nguồn khác và trừ ra khỏi prices
            double productPrice = cartItem.getQuantity() * cartItem.getProduct().getFinalPrice(); // Hàm này cần phải được triển khai
            prices -= productPrice;
        }
        System.out.println("prices: " + prices);
        session.setAttribute("prices", prices);
        resp.getWriter().write(String.valueOf(prices));
    }

    private void selectAllProductsOnCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        boolean isChecked = Boolean.parseBoolean(req.getParameter("isChecked"));
        HttpSession session = req.getSession();
        Double prices = (Double) session.getAttribute("prices");
        if (prices == null) {
            prices = 0.0;
        }
        Customer customer = (Customer) session.getAttribute("customer_login");
        List<CartItem> cartItems = cartItemDao.selectCartItemsByCartId(cartDao.selectByCustomerId(customer.getId()).getId());

        if (isChecked) {
            // Cộng giá tất cả sản phẩm trong giỏ hàng
            prices = 0.0;
            for (CartItem cartItem : cartItems) {
                if (importProductDao.selectToTalProductInStock(cartItem.getProduct().getId()) > 0) {
                    prices += cartItem.getQuantity() * cartItem.getProduct().getFinalPrice();
                }
            }

        } else {
            // Nếu bỏ chọn tất cả thì đặt lại giá bằng 0
            prices = 0.0;
        }

        session.setAttribute("prices", prices);
        resp.getWriter().write(String.valueOf(prices));
    }

    private void updateQuantityOnCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        Customer customer = (Customer) req.getSession().getAttribute("customer_login");
        String cartIdParam = req.getParameter("cartId");
        String action = req.getParameter("update");
        System.out.println("action:" + action);
        CartItem cartItem = cartItemDao.selectById(Integer.parseInt(cartIdParam));
        String status = "";
        if (action.equals("minus")) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            if (cartItem.getQuantity() == 0) {
                cartItemDao.delete(cartItem);
                status = "delete";
            } else {
                cartItemDao.update(cartItem);
                status = "update";
            }
        } else if (action.equals("plus")) {
            status = "update";
            if (importProductDao.selectToTalProductInStock(cartItem.getProduct().getId()) > cartItem.getQuantity()) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            }
            cartItemDao.update(cartItem);
        }
        int cartSize = cartDao.selectByCustomerId(customer.getId()).getTotalQuantity();
        JsonObject jsonResponse = new JsonObject();
        Gson gson = new Gson();
        double priceUpdate = cartItem.getProduct().getFinalPrice() * cartItem.getQuantity();
        jsonResponse.addProperty("status", status);
        jsonResponse.addProperty("cartSize", cartSize);
        jsonResponse.addProperty("quantity", cartItem.getQuantity());
        jsonResponse.addProperty("priceUpdate", priceUpdate);
        resp.getWriter().write(gson.toJson(jsonResponse));
    }

    private void removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        Customer customer = (Customer) req.getSession().getAttribute("customer_login");
        String cartIdParam = req.getParameter("cartId");
        CartItem cartItem = cartItemDao.selectById(Integer.parseInt(cartIdParam));
        cartItemDao.delete(cartItem);
        int cartSize = cartDao.selectByCustomerId(customer.getId()).getTotalQuantity();
        JsonObject jsonResponse = new JsonObject();
        Gson gson = new Gson();
        jsonResponse.addProperty("cartSize", cartSize);
        jsonResponse.addProperty("success", true);
        resp.getWriter().write(gson.toJson(jsonResponse));
    }

    private void goConfirmAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selectedProductIds = req.getParameterValues("selectedProducts");
        System.out.println(selectedProductIds.length);
        boolean error = false;
        String response = "";
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        HttpSession session = req.getSession();
        if (selectedProductIds != null) {

            for (String cartId : selectedProductIds) {
                System.out.println("id" + cartId);
                cartItems.add(cartItemDao.selectById(Integer.parseInt(cartId)));
            }
            for (CartItem cartItem : cartItems) {
                if (importProductDao.selectToTalProductInStock(cartItem.getProduct().getId()) < cartItem.getQuantity()) {
                    error = true;
                    response += cartItem.getProduct().getName() + " không đủ số lượng. ";
                    cartItem.setQuantity(importProductDao.selectToTalProductInStock(cartItem.getProduct().getId()));
                    cartItemDao.update(cartItem);
                }
            }
        }
        System.out.println("toi dang o day voi error:" + error);
        if (!error) {
            session.setAttribute("selectedCartItems", cartItems);
            String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath();
            resp.sendRedirect(link + "/customer/chonDiaChi.jsp");
        } else {
            session.setAttribute("response", response);
            session.setAttribute("selectedCartItems", cartItems);
            String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath();
            resp.sendRedirect(link + "/customer/gioHang.jsp");
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
        DataRespone dataRespone = ApiGHN.getData(addressDetail, wardId[0], districtId[0]);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("selectedCartItems");
        String fromAddress = "Khu Phố 6, Phường Linh Trung, Thành Phố Thủ Đức, Thành Phố Hồ Chí Minh ";
        double deliveryFee = Double.parseDouble(dataRespone.getDeliveryFee());
        Timestamp deliveryDate = TransDate.formate(dataRespone.getDeliveryDate());
        String note = "";
        double totalPrice = 0;
        Order order = new Order(customer, name, totalPrice, null, numberPhone, fromAddress, addressDetail, deliveryFee, deliveryDate, "", null, null, null, null);
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (CartItem cartItem : cartItems) {
            orderItems.add(new OrderItem(order, cartItem.getProduct(), cartItem.getQuantity()));
            totalPrice += cartItem.getProduct().getFinalPrice() * cartItem.getQuantity();
        }
        order.setOrderItems(orderItems);
        order.setTotal(totalPrice + deliveryFee);
        session.setAttribute("order", order);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/customer/xacNhanDatHang.jsp");
    }

    private void confirmVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String voucherCode = req.getParameter("voucherCode");
        Voucher voucher = voucherDao.selectByCode(voucherCode);
        JsonObject jsonResponse = new JsonObject();
        Gson gson = new Gson();
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        if (voucher == null) {
            jsonResponse.addProperty("status", "incorrect");
            System.out.println(order.getTotal());
            jsonResponse.addProperty("totalPrice", order.getTotal());
        } else {
            jsonResponse.addProperty("status", "correct");
            jsonResponse.addProperty("discount", voucher.getDiscount());
            double totalPrice = (order.getTotal() - voucher.getDiscount() < 0) ? 0 : order.getTotal() - voucher.getDiscount();
            jsonResponse.addProperty("totalPrice", totalPrice);
        }

        String json = gson.toJson(jsonResponse);
        resp.getWriter().write(json);
    }

    private void order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        String note = req.getParameter("note");
        String voucherCode = req.getParameter("voucher_code");
        String[] paymentMethods = req.getParameterValues("payment_method");
        Voucher voucher = voucherDao.selectByCode(voucherCode);
        order.setNote(note);
        order.setVoucher(voucher);
        order.setStatus(orderStatusDao.selectById(1));
        order.setDate(new Timestamp(System.currentTimeMillis()));
        if (voucher != null) {
            order.setTotal(order.getTotal() - order.getVoucher().getDiscount());
            if (order.getTotal() < 0) {
                order.setTotal(0);
            }
        } else {
            order.setTotal(order.getTotal());

        }

        if (paymentMethods[0].equals("2")) {
            String url = "";
            String amount = String.valueOf(order.getTotal());
            amount = amount.replaceAll("\\.0$", "");
            System.out.println(amount);
            url = ("http://localhost:8080/vnpay?amount=" + amount);
            resp.sendRedirect(url);

        } else {
            List<CartItem> cartItems = (List<CartItem>) session.getAttribute("selectedCartItems");
            for (CartItem cartItem : cartItems) {
                cartItemDao.delete(cartItem);
            }
            order.setPaymentMethod(paymentMethodDao.selectById(Integer.parseInt(paymentMethods[0])));
            int orderId = orderDao.insert(order);
            order.setId(orderId);
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setId(orderItem.getId());
                orderItemDao.insert(orderItem);
            }
            String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath();
            resp.sendRedirect(link + "/customer/datHangThanhCong.jsp");
        }
    }

    private void confirmBanK(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        order.setPaymentMethod(paymentMethodDao.selectById(2));
        int orderId = orderDao.insert(order);
        order.setId(orderId);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setId(orderItem.getId());
            orderItemDao.insert(orderItem);
        }
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("selectedCartItems");
        for (CartItem cartItem : cartItems) {
            cartItemDao.delete(cartItem);
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/customer/datHangThanhCong.jsp");
    }

    private void getOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer_login");
        String statusParam = req.getParameter("status");
        int status = Integer.parseInt(statusParam);
        System.out.println("da vao day " + status);
        ArrayList<Order> orders;
        if (status == 0) {
            orders = orderDao.selectByCustomerId(customer.getId());
        } else {
            orders = orderDao.selectByCustomerIdAndStatusId(customer.getId(), status);
        }
        session.setAttribute("status", status);
        if (orders.size() == 0) {
            session.setAttribute("empty", " Không có đơn hàng nào ");
            session.removeAttribute("orderStatusList");
        } else {
            session.setAttribute("orderStatusList", orders);
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/customer/trangThaiCacDonHang.jsp");
    }

    private void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String statusParam = req.getParameter("status");
        int status = Integer.parseInt(statusParam);
        String orderIdParam = req.getParameter("orderId");
        int orderId = Integer.parseInt(orderIdParam);
        Order order = orderDao.selectById(orderId);
        if (status == 5 && order.getStatus().getId() < 3) {
            order.setStatus(orderStatusDao.selectById(status));
            orderDao.update(order);
        } else {
            order.setStatus(orderStatusDao.selectById(status));
            orderDao.update(order);
         }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/customer?action=getOrderStatus&status=" + status);
    }

    private void detailOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String orderIdParam = req.getParameter("orderId");
        int orderId = Integer.parseInt(orderIdParam);
        Order order = orderDao.selectById(orderId);
        req.setAttribute("orderDetail", order);
        req.getRequestDispatcher("/customer/chiTietDonHang.jsp").forward(req, resp);

    }

    private void productDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String productIdParam = req.getParameter("productId");
        int productId = Integer.parseInt(productIdParam);
        Product productDetail = prodDao.selectById(productId);
        List<Product> productRelate = prodDao.selectProductRealte(productDetail);
        System.out.println(productRelate.size());
        req.setAttribute("productDetail", productDetail);
        req.setAttribute("productRelate", productRelate);
        req.getRequestDispatcher("/chiTietSanPham.jsp").forward(req, resp);
    }

    private void filterProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String[] categoryParams = req.getParameterValues("categories");
        String priceParam = req.getParameter("price");
        String discountParam = req.getParameter("discount");
        String sortByParam = req.getParameter("sortAction");
        String[] categories = categoryParams != null ? categoryParams : null;
        String price = priceParam != null ? priceParam : null;
        String discount = discountParam != null ? discountParam : null;
        String sort = (sortByParam != null && sortByParam.length() > 0) ? sortByParam : null;
        ArrayList<Product> productList = prodDao.selectProductByFilter(categories, price, discount, sort);
        Gson gson = new Gson();
        String json = gson.toJson(productList);
        System.out.println(json);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(json);
        out.flush();
    }

    private void searchByNameProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String nameProduct = req.getParameter("nameProduct");
        System.out.println(nameProduct);
        ArrayList<Product> products = prodDao.selectByNameProduct(nameProduct);
        HttpSession session = req.getSession();
        session.setAttribute("searchProduct", products);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/timKiemSanPham.jsp");
    }

    private void searchByParentCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String parentCateId = req.getParameter("parentCateId");
        ArrayList<Product> products = prodDao.selectProductsByParentCategoryId(Integer.parseInt(parentCateId));
        HttpSession session = req.getSession();
        session.setAttribute("searchProduct", products);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/timKiemSanPham.jsp");
    }
    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String password = req.getParameter("new-password");
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer_login");
        System.out.println(customer.toString());
        customer.setPassword(Encode.toSHA1(password));
        System.out.println(password);
        System.out.println(Encode.toSHA1(password));
        cusDao.update(customer);
        logout(req, resp);
    }
    private void changeInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String fullName = req.getParameter("full-name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer_login");
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setNumberPhone(phone);
        cusDao.update(customer);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/trangChu.jsp");
    }
    private void forgetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String username = req.getParameter("username");
        boolean error = false;
        HttpSession session = req.getSession();
        String url = "";
        if(!cusDao.checkUsername(username)){
            error = true;
            session.setAttribute("error_forgetPassword","Tài khoản không tồn tại trong hệ thống");
            url = "/quenMatKhau.jsp";
        }else{
            Customer customer = cusDao.selectByUsername(username);
            String resetCode = RandomNumberGenerator.generateRandomNumbersString();
            cusDao.updateResetCode(resetCode, customer.getUsername());
            Email.sendEmaiQuenMatKhau(customer, resetCode);
            session.setAttribute("customer_resetPassword", customer);
            url = "/datLaiMatKhau.jsp";
        }

        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + url);
    }
    private void resetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String resetCode = req.getParameter("resetCode");
        String password = req.getParameter("new-password");
        HttpSession session = req.getSession();
        String url = "";
        Customer customer = (Customer) session.getAttribute("customer_resetPassword");
        if(cusDao.checkResetCode(customer.getUsername(), resetCode)){
            customer.setPassword(Encode.toSHA1(password));
            cusDao.update(customer);
            session.setAttribute("register_success", "Đặt lại mật khẩu thành công");
            url = "/dangNhap.jsp";
        }else{
            url = "/datLaiMatKhau.jsp";
            session.setAttribute("error_resetCode", "Mã xác nhận không chính xác");

        }

        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + url);
    }
    private void goListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        int count = prodDao.countProduct();
        int endPage = count / 12;
        if(count % 12 != 0){
            endPage ++;
        }
        String indexParam = req.getParameter("index");
        ArrayList<Product> listProduct = prodDao.selectPaging(Integer.parseInt(indexParam));
        session.setAttribute("listProductPaging", listProduct);
        session.setAttribute("endPage", endPage);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/sanPham.jsp");
    }

}