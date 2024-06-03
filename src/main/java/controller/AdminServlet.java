package controller;

import database.*;
import model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private ParentCategoryDao parentCateDao = new ParentCategoryDao();
    private CategoryDao categoryDao = new CategoryDao();
    private DiscountDao discountDao = new DiscountDao();
    private ProductDao productDao = new ProductDao();
    private VoucherDao voucherDao = new VoucherDao();
    private OrderStatusDao orderStatusDao = new OrderStatusDao();
    private PaymentMethodDao paymentMethodDao = new PaymentMethodDao();
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    private ImportProductDao importProductDao = new ImportProductDao();
    private LogDao logDao = new LogDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addParentCategory".equals(action)) {
            addParentCategory(req, resp);
        } else if ("addCategory".equals(action)) {
        } else if ("addDiscount".equals(action)) {
        } else if ("addProduct".equals(action)) {
            addProduct(req, resp);
        } else if (action.equals("updateOrder")) {
            updateOrder(req, resp);
        } else if (action.equals("getOrderStatus")) {
            getOrderStatus(req, resp);
        } else if (action.equals("detailOrder")) {
            detailOrder(req, resp);
        } else if(action.equals("importProduct")){
            importProduct(req, resp);
        } else if(action.equals("deleteLog")){
            deleteLog(req, resp);
        } else if(action.equals("detailLog")){
            detailLog(req, resp);
        } else if(action.equals("goUpdateProduct")){
            goUpdateProduct(req, resp);
        } else if(action.equals("updateProduct")){
            updateProduct(req, resp);
        } else if(action.equals("deleteProduct")){
            deleteProduct(req, resp);
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

    }
