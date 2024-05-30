package excel;

import database.ProductDao;
import model.Category;
import model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.JDBCUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static List<Product> readProductsFromExcel(String filePath) {
        List<Product> products = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Bỏ qua hàng đầu tiên nếu đó là tiêu đề
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Product product = createProductFromRow(row);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private static Product createProductFromRow(Row row) {

        int id = getIntCellValue(row.getCell(0));
        String name = getStringCellValue(row.getCell(1));
        String description = getStringCellValue(row.getCell(2));
        double price = getNumericCellValue(row.getCell(3));
        String imageUrl = getStringCellValue(row.getCell(4));
        String unit = getStringCellValue(row.getCell(5));
        double weight = getNumericCellValue(row.getCell(6));
        boolean available = getBooleanCellValue(row.getCell(7));

        String categoryName = extractCategoryNameFromImageUrl(imageUrl);
        // Lấy id của danh mục từ cơ sở dữ liệu
        int categoryId = getCategoryId(categoryName);

        Category category = new Category(categoryId);

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setUnit(unit);
        product.setWeight(weight);
        product.setAvailable(available);
        product.setCategory(category);

        return product;
    }

    private static String extractCategoryNameFromImageUrl(String imageUrl) {
        String[] pathSegments = imageUrl.split("\\\\"); // Tách đường dẫn thành các phần tử
        if (pathSegments.length >= 2) {
            return pathSegments[pathSegments.length - 2]; // Lấy phần tử trước phần tử cuối cùng
        }
        return null; // Trả về null nếu không tìm thấy tên thư mục

    }

    private static int getIntCellValue(Cell cell) {
        if (cell == null) {
            return 0;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1 : 0;
            default:
                return 0;
        }
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return null;
        }
    }

    private static double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1.0 : 0.0;
            default:
                return 0.0;
        }
    }

    private static boolean getBooleanCellValue(Cell cell) {
        if (cell == null) {
            return false;
        }
        switch (cell.getCellType()) {
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue() != 0;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        String excelFilePath = "C:\\IntelliJ\\FreshFoodWeb\\products.xlsx";
        List<Product> products = readProductsFromExcel(excelFilePath);

        ProductDao productDao = new ProductDao();
        int batchSize = 1; // Kích thước của mỗi đợt chèn
        try {
            for (int i = 0; i < products.size(); i += batchSize) {
                Connection con = JDBCUtil.getConnection();
                con.setAutoCommit(false);
                try {
                    for (int j = i; j < i + batchSize && j < products.size(); j++) {
                        productDao.insert(products.get(j)); // Chèn sản phẩm vào bảng Product
                    }
                    con.commit();
                    System.out.println("Dữ liệu đợt từ " + i + " đến " + (i + batchSize - 1) + " đã được chèn thành công vào cơ sở dữ liệu.");
                } catch (SQLException e) {
                    con.rollback();
                    e.printStackTrace();
                } finally {
                    JDBCUtil.closeConnection(con);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static int getCategoryId(String categoryName) {
        int categoryId = 0; // Khởi tạo id danh mục

        // Thực hiện truy vấn SQL để lấy id của danh mục với tên categoryName
        String query = "SELECT id FROM categories WHERE name = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categoryName); // Thiết lập tham số cho truy vấn

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    categoryId = resultSet.getInt("id"); // Lấy id từ kết quả truy vấn
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý các ngoại lệ kết nối hoặc truy vấn SQL
        }

        return categoryId; // Trả về id của danh mục tương ứng
    }

}
