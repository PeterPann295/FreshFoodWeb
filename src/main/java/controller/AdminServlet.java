package controller;

import database.CategoryDao;
import database.DiscountDao;
import database.ParentCategoryDao;
import database.ProductDao;
import model.Category;
import model.Discount;
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
    private final CategoryDao categoryDao = new CategoryDao();
    private final DiscountDao discountDao = new DiscountDao();
    private final ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("addParentCategory")) {
            String name = req.getParameter("name");
            String imageURL = req.getParameter("imageURL");

            ParentCategory parentCategory = new ParentCategory(name, imageURL);
            ParentCategoryDao parentCategoryDao = new ParentCategoryDao();
            parentCategoryDao.insert(parentCategory);

            resp.sendRedirect("adminAddProduct.jsp");
        } else if ("addProduct".equals(action)) {
            addProduct(req, resp);
        }
    }

    private void addParentCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xử lý thêm danh mục cha
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File("C:/IntelliJ/FreshFoodWeb/src/main/webapp"));
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);

        // Khởi tạo các biến để lưu thông tin sản phẩm
        String productName = "";
        double price = 0;
        String unit = "";
        double weight = 0;
        String imgProduct = "";
        boolean availables = false;
        int categoryId = 0;
        int discountId = 0;
        String description = "";

        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    // Xử lý trường thông tin không phải là file
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString("UTF-8");

                    // Xác định giá trị của từng trường thông tin
                    switch (fieldName) {
                        case "productName":
                            productName = fieldValue;
                            break;
                        case "price":
                            price = Double.parseDouble(fieldValue);
                            break;
                        case "unit":
                            unit = fieldValue;
                            break;
                        case "weight":
                            weight = Double.parseDouble(fieldValue);
                            break;
                        case "availables":
                            availables = Boolean.parseBoolean(fieldValue);
                            break;
                        case "category":
                            categoryId = Integer.parseInt(fieldValue);
                            break;
                        case "discount":
                            if ("none".equals(fieldValue)) {
                                discountId = 0;
                            } else {
                                discountId = Integer.parseInt(fieldValue);
                            }
                            break;
                        case "description":
                            description = fieldValue;
                            break;
                    }
                } else {
                    // Xử lý trường thông tin là file (ảnh sản phẩm)
                    if ("imgProduct".equals(fileItem.getFieldName())) {
                        File file = new File("C:/IntelliJ/FreshFoodWeb/src/main/webapp/assets/images/products/" + fileItem.getName());
                        fileItem.write(file);
                        imgProduct = "/assets/images/products/" + fileItem.getName();
                    }
                }
            }

            // Kiểm tra và tạo sản phẩm mới
            if (!productName.isEmpty() && price > 0 && !unit.isEmpty() && weight > 0 && !imgProduct.isEmpty() && categoryId > 0) {
                Category category = categoryDao.selectById(categoryId);
                Discount discount = (discountId > 0) ? discountDao.selectById(discountId) : null;
                Product product = new Product(productName, description, price, imgProduct, unit, weight, availables, 0, category, discount);
                productDao.insert(product);
            } else {
                // Xử lý lỗi: thiếu thông tin sản phẩm
                throw new ServletException("Thiếu thông tin sản phẩm");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        // Chuyển hướng sau khi thêm sản phẩm
        resp.sendRedirect(req.getContextPath() + "/admin/danhMucCha.jsp");
    }
}
