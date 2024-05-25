package controller;

import database.*;
import model.Category;
import model.ParentCategory;
import model.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private ParentCategoryDao parentCateDao = new ParentCategoryDao();
    private CategoryDao categoryDao = new CategoryDao();
    private DiscountDao discountDao = new DiscountDao();
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("listCustomer".equals(action)) {
            listCustomer(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addParentCategory".equals(action)) {
            addParentCategory(req, resp);
        } else if ("addCategory".equals(action)) {
            addCategory(req, resp);
        } else if ("addDiscount".equals(action)) {
        } else if ("addProduct".equals(action)) {
            addProduct(req, resp);
        }   else if ("editRoleCustomer".equals(action)) {
            editRoleCustomer(req, resp);
        }

    }
    private void listCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDao customerDao = new CustomerDao();
        req.setAttribute("customers", customerDao.selectAll());
        req.getRequestDispatcher("/admin/adminCustomer.jsp").forward(req, resp);
    }
    private void editRoleCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        int role = Integer.parseInt(req.getParameter("role"));

        CustomerDao customerDao = new CustomerDao();
        customerDao.updateRole(id, role);

        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        resp.sendRedirect(link + "/admin/adminCustomer.jsp");
    }
    private void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File("C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\"));
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);

        String name = "";
        int parentCateId = 0;

        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    if ("nameCate".equals(fileItem.getFieldName())) {
                        name = fileItem.getString("UTF-8");
                    }
                    if ("parentCate".equals(fileItem.getFieldName())) {
                        parentCateId = Integer.parseInt(fileItem.getString("UTF-8"));
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        categoryDao.insert(new Category(name, parentCateDao.selectById(parentCateId)));

        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        System.out.println(link);
        resp.sendRedirect(link + "/admin/adminProduct.jsp");
    }

    private void addParentCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File("C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\"));
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
                        File file = new File("C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\assets\\images\\categories\\" + fileItem.getName());
                        fileItem.write(file);
                        url = "/assets/images/categories/" + fileItem.getName();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            parentCateDao.insert(new ParentCategory(name, url));

        String link = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath();
        System.out.println(link);
        resp.sendRedirect(link + "/admin/adminProduct.jsp");
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File("C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\"));
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
        resp.sendRedirect(link + "/admin/adminProduct.jsp");
    }
}