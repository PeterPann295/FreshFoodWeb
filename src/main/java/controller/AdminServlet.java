package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.*;
import model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.Email;
import service.*;
import utils.Encode;
import utils.OrderSummary;
import utils.OrderSummaryYear;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private ParentCategoryService parentCateDao = new ParentCategoryService();
    private CategoryService categoryDao = new CategoryService();
    private DiscountService discountDao = new DiscountService();
    private ProductService productDao = new ProductService();
    private VoucherDao voucherDao = new VoucherDao();
    private OrderStatusService orderStatusDao = new OrderStatusService();
    private OrderService orderDao = new OrderService();
    private ImportProductService importProductDao = new ImportProductService();
    private LogDao logDao = new LogDao();
    private CustomerService customerDao = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "addParentCategory":
                addParentCategory(req, resp);
                break;
            case "addProduct":
                addProduct(req, resp);
                break;
            case "updateOrder":
                updateOrder(req, resp);
                break;
            case "getOrderStatus":
                getOrderStatus(req, resp);
                break;
            case "detailOrder":
                detailOrder(req, resp);
                break;
            case "importProduct":
                importProduct(req, resp);
                break;
            case "deleteLog":
                deleteLog(req, resp);
                break;
            case "detailLog":
                detailLog(req, resp);
                break;
            case "goUpdateProduct":
                goUpdateProduct(req, resp);
                break;
            case "updateProduct":
                updateProduct(req, resp);
                break;
            case "deleteProduct":
                deleteProduct(req, resp);
                break;
            case "goUpdateCustomer":
                goUpdateCustomer(req, resp);
                break;
            case "updateCustomer":
                updateCustomer(req, resp);
                break;
            case "deleteCustomer":
                deleteCustomer(req, resp);
                break;
            case "getTotalRevenue7Days":
                getTotalRevenue7Days(req, resp);
                break;
            case "addVoucher":
                addVoucher(req, resp);
                break;
            case "goUpdateVoucher":
                goUpdateVoucher(req, resp);
                break;
            case "deleteVoucher":
                deleteVoucher(req, resp);
                break;
            case "updateVoucher":
                updateVoucher(req, resp);
                break;
            case "addDiscount":
                addDiscount(req, resp);
                break;
            case "goUpdateDiscount":
                goUpdateDiscount(req, resp);
                break;
            case "updateDiscount":
                updateDiscount(req, resp);
                break;
            case "deleteDiscount":
                deleteDiscount(req, resp);
                break;
            case "getTotalRevenueEveryYear":
                getTotalRevenueEveryYear(req, resp);
                break;
            case "addAdmin":
                addAdmin(req, resp);
            case "addContact":
                addContact(req, resp);
                break;
            case "contact":
                contact(req, resp);
                break;
            case "sendMail":
                sendMail(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }
    private void addContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String content = request.getParameter("content");

        Contact contact = new Contact(name, phone, email, content);
        ContactDAO dao = new ContactDAO();
        dao.insert(contact);
        request.setAttribute("respone", "Bạn đã gửi thành công!");
        request.getRequestDispatcher("/customer/lienHe.jsp").forward(request, response);
    }
    private void contact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contactID = request.getParameter("contactID");

        int id = Integer.parseInt(contactID);

        ContactDAO dao = new ContactDAO();
        ArrayList<Contact> contacts = dao.selectByID(id);

        if (!contacts.isEmpty()) {
            Contact contact = contacts.get(0);
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("/admin/phanHoiAdmin.jsp").forward(request, response);
        } else {
            // Xử lý khi không tìm thấy thông tin liên hệ
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy thông tin liên hệ");
        }
    }

    private void sendMail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contactID = request.getParameter("contactID");

        int id = Integer.parseInt(contactID);

        String tieuDe = request.getParameter("tieuDe");
        String noiDung = request.getParameter("noiDung");

        ContactDAO dao = new ContactDAO();
        ArrayList<Contact> contacts = dao.selectByID(id);

        if (!contacts.isEmpty()) {
            Contact contact = contacts.get(0);

            // Gửi email và xóa liên hệ sau khi đã xử lý
            Email email = new Email();
            email.sendEmail(contact.getEmail(), tieuDe, noiDung);
            dao.delete(contact);

            // Chuyển hướng về trang danh sách liên hệ của admin
            request.getRequestDispatcher("/admin/lienHeAdmin.jsp").forward(request, response);
        } else {
            // Xử lý khi không tìm thấy thông tin liên hệ
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy thông tin liên hệ");
        }
    }


    private void addDiscount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int percent = Integer.parseInt(req.getParameter("percent"));

        Discount discount = new Discount(name, percent);
        discountDao.insert(discount);
        resp.sendRedirect(req.getContextPath() + "/admin/discount.jsp");
    }


    private void goUpdateDiscount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String discountIdParam = req.getParameter("discountId");
        Discount discount = discountDao.selectById(Integer.parseInt(discountIdParam));

        HttpSession session = req.getSession();
        session.setAttribute("discount", discount);

        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/capnhatDiscount.jsp");
    }
    private void updateDiscount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String discountIdStr = req.getParameter("discountId");
        if (discountIdStr != null && !discountIdStr.isEmpty()) {
            int id = Integer.parseInt(discountIdStr);
            String code = req.getParameter("name");
            int value = Integer.parseInt(req.getParameter("percent"));

            Discount discount = new Discount(id, code, value);
            discountDao.update(discount);

            resp.sendRedirect(req.getContextPath() + "/admin/discount.jsp");
        } else {
            throw new ServletException("Thiếu thông tin discountId");
        }
    }
    private void deleteDiscount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String discountIdStr = req.getParameter("discountId");
        if (discountIdStr != null && !discountIdStr.isEmpty()) {
            int id = Integer.parseInt(discountIdStr);

            Discount discount = new Discount();
            discount.setId(id);
            discountDao.delete(discount);

            resp.sendRedirect(req.getContextPath() + "/admin/discount.jsp");
        } else {
            throw new ServletException("Thiếu thông tin discountId");
        }
    }

    private void addVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        double discount = Double.parseDouble(req.getParameter("discount"));

        Voucher voucher = new Voucher(code, discount);
        voucherDao.insert(voucher);
        resp.sendRedirect(req.getContextPath() + "/admin/voucher.jsp");
    }

    private void goUpdateVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String voucherIdParam = req.getParameter("voucherId");
        Voucher voucher = voucherDao.selectById(Integer.parseInt(voucherIdParam));

        HttpSession session = req.getSession();
        session.setAttribute("voucher", voucher);

        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/updateVoucher.jsp");
    }


    private void updateVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String voucherIdStr = req.getParameter("voucherId");
        if (voucherIdStr != null && !voucherIdStr.isEmpty()) {
            int id = Integer.parseInt(voucherIdStr); // Chuyển đổi voucherId thành số nguyên
            String code = req.getParameter("code");
            double discount = Double.parseDouble(req.getParameter("discount"));

            Voucher voucher = new Voucher(id, code, discount);
            voucherDao.update(voucher); // Cập nhật voucher

            resp.sendRedirect(req.getContextPath() + "/admin/voucher.jsp"); // Chuyển hướng về trang danh sách voucher
        } else {
            throw new ServletException("Thiếu thông tin voucherId");
        }
    }



    private void deleteVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String voucherIdStr = req.getParameter("voucherId");
        if (voucherIdStr != null && !voucherIdStr.isEmpty()) {
            int id = Integer.parseInt(voucherIdStr); // Chuyển đổi voucherId thành số nguyên

            Voucher voucher = new Voucher();
            voucher.setId(id);
            voucherDao.delete(voucher); // Xóa voucher

            resp.sendRedirect(req.getContextPath() + "/admin/voucher.jsp"); // Chuyển hướng về trang danh sách voucher
        } else {
            throw new ServletException("Thiếu thông tin voucherId");
        }
    }

    private void addParentCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File("D:/FutureOfMe/ProjectWeb/FreshFoodWeb/src/main/webapp"));
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);

        String url = "";
        String name = "";

        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    if ("namePC".equals(fileItem.getFieldName())) {
                        name = fileItem.getString("UTF-8");
                    }
                } else {
                    if ("imgCate".equals(fileItem.getFieldName())) {
                        File file = new File("D:/FutureOfMe/ProjectWeb/FreshFoodWeb/src/main/webapp/assets/images/categories/" + fileItem.getName());
                        fileItem.write(file);
                        url = "/assets/images/categories/" + fileItem.getName();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Đảm bảo cả name và url đều được lấy đúng
        if (!name.isEmpty() && !url.isEmpty()) {
            parentCateDao.insert(new ParentCategory(name, url));
        } else {
            // Xử lý lỗi: thiếu name hoặc url
            throw new ServletException("Thiếu dữ liệu biểu mẫu");
        }

        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        System.out.println(link);
        resp.sendRedirect(link + "/admin/danhMucCha.jsp");
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File("D:/FutureOfMe/ProjectWeb/FreshFoodWeb/src/main/webapp"));
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);

        String productName = "";
        double price = 0;
        String unit = "";
        double weight = 0;
        String imgProduct = "";
        boolean availables = false;
        int categoryId = 0;
        int discountId = 0;
        String description = "";
        int count = 0;
        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) { // Xử lý các trường thông tin không phải là file
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString("UTF-8"); // Lấy giá trị của trường dữ liệu
                    System.out.println(fieldValue);
                    switch (fieldName) {
                        case "productName":
                            productName = fieldValue;
                            count ++;
                            break;
                        case "price":
                            price = Double.parseDouble(fieldValue);
                            count ++;
                            break;
                        case "unit":
                            unit = fieldValue;
                            count ++;
                            break;
                        case "weight":
                            weight = Double.parseDouble(fieldValue);
                            count ++;
                            break;
                        case "imgProduct":
                            imgProduct = fieldValue;
                            count ++;
                            break;
                        case "availables":
                            availables = Boolean.parseBoolean(fieldValue);
                            count ++;
                            break;
                        case "category":
                            categoryId = Integer.parseInt(fieldValue);
                            count ++;
                            break;
                        case "discount":
                            if(fieldValue.equals("none")){
                                discountId = 0;
                                count ++;
                                break;
                            }
                            discountId = Integer.parseInt(fieldValue);
                            count ++;
                            break;
                        case "description":
                            description = fieldValue;
                            count ++;
                            break;
                        default:
                            // Xử lý trường dữ liệu khác nếu cần
                    }
                } else { // Xử lý trường thông tin là file
                    if ("imgProduct".equals(fileItem.getFieldName())) {
                        File file = new File("D:/FutureOfMe/ProjectWeb/FreshFoodWeb/src/main/webapp/assets/images/products/" + fileItem.getName());
                        fileItem.write(file);
                        imgProduct = "/assets/images/products/" + fileItem.getName();
                        count ++;
                    }
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        if(count == 9){
            if(discountId == 0){
                Product product = new Product(productName, description, price, imgProduct, unit, weight, availables, categoryDao.selectById(categoryId), null);
                productDao.insert(product);
            }else {
                Product product = new Product(productName, description, price, imgProduct, unit, weight, availables, categoryDao.selectById(categoryId), discountDao.selectById(discountId));
                productDao.insert(product);
            }

        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/sanPham.jsp");
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
        }else {
            order.setStatus(orderStatusDao.selectById(status));
            orderDao.update(order);
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin?action=getOrderStatus&status=" + status);
    }
    private void getOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        String statusParam = req.getParameter("status");
        int status = Integer.parseInt(statusParam);
        System.out.println("da vao day " + status);
        ArrayList<Order> orders;
        if (status == 0) {
            orders = orderDao.selectAll();
        } else {
            orders = orderDao.selectByStatusId(status);
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
        resp.sendRedirect(link + "/admin/trangThaiCacDonHang.jsp");
    }
    private void detailOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        String orderIdParam = req.getParameter("orderId");
        int orderId = Integer.parseInt(orderIdParam);
        Order order = orderDao.selectById(orderId);
        session.setAttribute("orderDetail", order);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/chiTietDonHang.jsp");

    }
    private void importProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer_login");
        String productIdParam = req.getParameter("productId");
        System.out.println("id product: " + productIdParam);
        String quatityParam = req.getParameter("quatity");
        int quatity = Integer.parseInt(quatityParam);
        Product product = productDao.selectById(Integer.parseInt(productIdParam));
        System.out.println(product);
        if(quatity > 0){
            ImportProduct importProduct = new ImportProduct(product, Integer.parseInt(quatityParam),customer);
            importProductDao.insert(importProduct);
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/nhapSanPham.jsp");
    }
    private void deleteLog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String[] logIdParam = req.getParameterValues("selectedLog");
        for (String id: logIdParam){
            logDao.deleteById(Integer.parseInt(id));
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/quanLyLog.jsp");
    }
    private void detailLog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String logIdParam = req.getParameter("logId");
        Log log = logDao.selectById(Integer.parseInt(logIdParam));
        HttpSession session = req.getSession();
        session.setAttribute("log", log);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/chiTietLog.jsp");
    }

    private void goUpdateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String productIdParam = req.getParameter("productId");
        Product product = productDao.selectById(Integer.parseInt(productIdParam));
        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/capNhatSanPham.jsp");

    }
    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File("D:/FutureOfMe/ProjectWeb/FreshFoodWeb/src/main/webapp"));
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);

        String productId = req.getParameter("productId");
        Product product = productDao.selectById(Integer.parseInt(productId));
        String productName = null;
        double price = 0;
        String unit = null;
        double weight = 0;
        String imgProduct = null;
        boolean availables = false;
        int categoryId = 0;
        int discountId = 0;
        String description = "";
        int count = 0;
        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) { // Xử lý các trường thông tin không phải là file
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString("UTF-8"); // Lấy giá trị của trường dữ liệu
                    System.out.println(fieldValue);
                    switch (fieldName) {

                        case "productName":
                            productName = fieldValue;
                            if(productName != null){
                                product.setName(productName);
                            }
                            break;
                        case "price":
                            price = Double.parseDouble(fieldValue);
                            if(price > 0){
                                product.setPrice(price);
                            }
                            break;
                        case "unit":
                            unit = fieldValue;
                            if(unit != null){
                                product.setUnit(unit);
                            }
                            break;
                        case "weight":
                            weight = Double.parseDouble(fieldValue);
                            if(weight > 0){
                                product.setWeight(weight);
                            }
                            break;

                        case "availables":
                            availables = Boolean.parseBoolean(fieldValue);
                            product.setAvailable(availables);
                            break;
                        case "category":
                            categoryId = Integer.parseInt(fieldValue);
                            product.setCategory(categoryDao.selectById(categoryId));
                            break;
                        case "discount":
                            if(fieldValue.equals("none")){
                                product.setDiscount(null);
                                break;
                            }
                            discountId = Integer.parseInt(fieldValue);
                            product.setDiscount(discountDao.selectById(discountId));
                            break;
                        case "description":
                            description = fieldValue;
                            if (description != null){
                                product.setDescription(description);
                            }
                            break;
                        default:

                    }
                } else {
                    if ("imgProduct".equals(fileItem.getFieldName())) {
                        String fileName = fileItem.getName();
                        if(fileName != null && !fileName.isEmpty()){
                            File file = new File("D:/FutureOfMe/ProjectWeb/FreshFoodWeb/src/main/webapp/assets/images/products/" + fileName);
                            fileItem.write(file);
                            imgProduct = "/assets/images/products/" + fileName;
                        }
                    }

                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        if(imgProduct != null){
            product.setImageUrl(imgProduct);
        }
        productDao.update(product);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/sanPham.jsp");


    }
    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String productIdParam = req.getParameter("productId");
        Product product = productDao.selectById(Integer.parseInt(productIdParam));
        HttpSession session = req.getSession();
        if(productDao.delete(product) < 1){
            session.setAttribute("response", "Không thể xóa sản phẩm");
        }else{
            session.setAttribute("response", "Xóa sản phẩm thành công");
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/sanPham.jsp");
    }
    private void goUpdateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String customerIdParam = req.getParameter("customerId");
        System.out.println("id: " + customerIdParam);
        Customer customer = customerDao.selectById(Integer.parseInt(customerIdParam));
        HttpSession session = req.getSession();
        session.setAttribute("updateCustomer", customer);
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/capNhatKhachHang.jsp");
    }
    private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String customerIdParam = req.getParameter("customerId");
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("numberPhone");
        String email = req.getParameter("email");
        Customer customer = customerDao.selectById(Integer.parseInt(customerIdParam));
        System.out.println(fullName + phone + email);
        if(fullName != null && !fullName.isEmpty()){
            customer.setFullName(fullName);
        }
        if(phone != null && !phone.isEmpty()){
            customer.setNumberPhone(phone);
        }
        if(email != null && !email.isEmpty()){
            customer.setEmail(email);
        }
        System.out.println(customer);
        HttpSession session = req.getSession();
        int i = customerDao.update(customer);
        if( i > 0){
            session.setAttribute("response", "Cập Nhật Thành Công ");
        }else {
            session.setAttribute("response", "Cập Nhật Thất Bại ");
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
        resp.sendRedirect(link + "/admin/khachHang.jsp");
    }
    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String customerIdParam = req.getParameter("customerId");
        Customer customer = customerDao.selectById(Integer.parseInt(customerIdParam));
        HttpSession session = req.getSession();

        if(customerDao.delete(customer) < 1){
            session.setAttribute("response", "Xóa Thất Bại ");
        }else {
            session.setAttribute("response", "Xóa Thành Công ");
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
        resp.sendRedirect(link + "/admin/khachHang.jsp");
    }
    private void getTotalRevenue7Days(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        ArrayList<OrderSummary> orderSummaries = orderDao.getTotalRevenue7Days();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(orderSummaries);
        resp.getWriter().write(json);
    }
    private void getTotalRevenueEveryYear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        ArrayList<OrderSummaryYear> orderSummaries = orderDao.getTotalRevenueEveryYear();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(orderSummaries);
        resp.getWriter().write(json);
    }

    private void addAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("confirm-password", confirmPassword);
        session.setAttribute("fullName", fullName);
        session.setAttribute("phone", phone);
        session.setAttribute("email", email);
        boolean error = false;
        String url = "";
        if (customerDao.checkUsername(username)) {
            session.setAttribute("err_username", "Tên Đăng Nhập Đã Tồn Tại, Vui Lòng Chọn Tên Đăng Nhập Khác");
            url = "/admin/themNguoiDungAdmin.jsp";
            error = true;
        }
        if (!password.equals(confirmPassword)) {
            session.setAttribute("err_password", "Nhập Mật Khẩu Không Khớp");
            url = "/admin/themNguoiDungAdmin.jsp";
            error = true;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);

        if (!emailMatcher.matches()) {
            error = true;
            url = "/admin/themNguoiDungAdmin.jsp";
            session.setAttribute("err_email", "Câu trúc của email chưa đúng!");
        }

        // Kiểm tra số diện thoại
        Pattern soDienThoaiPattern = Pattern.compile("\\d{10}");
        Matcher soDienThoaiMatcher = soDienThoaiPattern.matcher(phone);
        if (!soDienThoaiMatcher.matches()) {
            error = true;
            url = "/admin/themNguoiDungAdmin.jsp";
            session.setAttribute("err_phone", "Số điện thoại bao gồm 10 ký tự!");
        }
        if (username.length() < 8) {
            error = true;
            url = "/admin/themNguoiDungAdmin.jsp";
            session.setAttribute("err_username", "Tên đăng nhập phải từ 6 kí tự");
        }
        if (password.length() < 8) {
            error = true;
            url = "/admin/themNguoiDungAdmin.jsp";
            session.setAttribute("err_password", "Mật khẩu tối thiểu 6 kí tự");
        }
        if (!error) {
            Customer cus = new Customer(username, Encode.toSHA1(password), fullName, phone, email);
            cus.setRole(true);
            customerDao.insert(cus);
            session.setAttribute("register_success", "Chức mừng bạn đăng kí thành công, vui lòng đăng nhập");
            url = "/admin/admin.jsp";
        }
        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
        resp.sendRedirect(link + url);    }

}
