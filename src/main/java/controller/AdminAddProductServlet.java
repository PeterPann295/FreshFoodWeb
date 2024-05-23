package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Discount;
import model.Product;
import database.CategoryDao;
import database.DiscountDao;
import database.ProductDao;

@WebServlet("/AdminAddProductServlet")
@MultipartConfig
public class AdminAddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ request
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int available = Integer.parseInt(request.getParameter("available"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        int discountPercent = Integer.parseInt(request.getParameter("discountPercent"));
        InputStream imageStream = request.getPart("image").getInputStream();

        // Lấy danh mục từ ID
        Category category = new CategoryDao().selectById(categoryId);

        // Kiểm tra xem có giảm giá không
        Discount discount = null;
        if (discountPercent > 0) {
            discount = new Discount("Discount", discountPercent);
        }

        // Lưu hình ảnh vào thư mục trên server
        String imagePath = saveImage(imageStream);
        // Tạo sản phẩm mới
        Product product = new Product(name, description, price, imagePath, "kg", 0, available == 1, 1, category, discount);

        // Thêm sản phẩm vào cơ sở dữ liệu
        ProductDao productDao = new ProductDao();
        productDao.insert(product);

        // Chuyển hướng về trang danh sách sản phẩm
        response.sendRedirect("pageAdmin_Product.jsp");
    }

    // Phương thức để lưu hình ảnh vào thư mục trên server và trả về đường dẫn tương đối
    private String saveImage(InputStream imageStream) throws IOException {
        String uploadPath = "path/to/your/upload/directory"; // Thay đổi đường dẫn tới thư mục lưu ảnh
        String imageName = "product_" + System.currentTimeMillis() + ".jpg";
        Files.copy(imageStream, Paths.get(uploadPath, imageName));
        return "path/to/your/upload/directory/" + imageName; // Thay đổi đường dẫn trả về nếu cần
    }
}
