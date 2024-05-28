package excel;

import model.Category;
import model.Discount;
import model.ParentCategory;
import model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExcelExporter {

    public void exportProductsToExcel(List<Product> products, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");

        // Tạo tiêu đề
        String[] columnHeaders = {
                "ID", "Name", "Description", "Price", "Image URL", "Unit",
                "Weight", "Available", "Status", "Category", "Discount"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

        // Tạo hàng dữ liệu
        int rowNum = 1;
        for (Product product : products) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getDescription());
            row.createCell(3).setCellValue(product.getPrice());
            row.createCell(4).setCellValue(product.getImageUrl());
            row.createCell(5).setCellValue(product.getUnit());
            row.createCell(6).setCellValue(product.getWeight());
            row.createCell(7).setCellValue(product.isAvailable());
            row.createCell(8).setCellValue(product.getStatus());
            row.createCell(9).setCellValue(product.getCategory().getName()); // Tên danh mục
            row.createCell(10).setCellValue(product.getDiscount() != null ? product.getDiscount().getName() : ""); // Tên discount
        }

        // Ghi file Excel ra ổ đĩa
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    public static void main(String[] args) {
        String filePath = "C:\\IntelliJ\\FreshFoodWeb\\products.xlsx";
        String imagePath = "C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\assets\\images\\categories";
        try {
            List<ParentCategory> parentCategories = ParentCategoryExtractor.extractParentCategories(imagePath);
            List<Product> sampleProducts = generateSampleProducts(70, parentCategories);
            new ExcelExporter().exportProductsToExcel(sampleProducts, filePath);
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tạo danh sách sản phẩm mẫu với các danh mục cha khác nhau
    public static List<Product> generateSampleProducts(int numberOfProducts, List<ParentCategory> parentCategories) {
        List<Product> products = new ArrayList<>();
        Random random = new Random();

        int productsPerCategory = numberOfProducts / parentCategories.size();
        int productId = 1;

        for (ParentCategory parentCategory : parentCategories) {
            for (int i = 0; i < productsPerCategory; i++) {
                Product product = new Product();
                product.setId(productId++);
                product.setName("Product " + productId);
                product.setDescription("Description for product " + productId);
                product.setPrice(100.0 + random.nextDouble() * 900.0);
                product.setImageUrl(parentCategory.getImageURL()); // Sử dụng URL ảnh từ danh mục cha
                product.setUnit("Unit " + productId);
                product.setWeight(random.nextDouble() * 10);
                product.setAvailable(random.nextBoolean());
                product.setStatus(random.nextInt(2)); // 0 or 1 for status
                product.setCategory(new Category(parentCategory.getName(), parentCategory)); // Danh mục cha
                product.setDiscount(generateRandomDiscount()); // Discount ngẫu nhiên

                products.add(product);
            }
        }

        return products;
    }

    // Tạo discount ngẫu nhiên
    private static Discount generateRandomDiscount() {
        Random random = new Random();
        if (random.nextBoolean()) {
            int id = random.nextInt(100) + 1;
            return new Discount(id, "Discount " + id, random.nextInt(30));
        }
        return null;
    }
}
