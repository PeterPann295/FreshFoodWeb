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
            row.createCell(10).setCellValue(product.getDiscount().getName()); // Tên discount (nếu có)
        }

        // Ghi file Excel ra ổ đĩa
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    public static void main(String[] args) {
        String filePath = "C:\\IntelliJ\\FreshFoodWeb\\products.xlsx";
        try {
            List<Product> sampleProducts = generateSampleProducts(100);
            new ExcelExporter().exportProductsToExcel(sampleProducts, filePath);
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tạo danh sách sản phẩm mẫu với các danh mục cha khác nhau
    public static List<Product> generateSampleProducts(int numberOfProducts) {
        // Tạo danh sách các sản phẩm mẫu
        List<Product> products = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= numberOfProducts; i++) {
            // Tạo sản phẩm ngẫu nhiên
            Product product = new Product();
            product.setId(i);
            product.setName("Product " + i);
            product.setDescription("Description for product " + i);
            product.setPrice(100.0 + random.nextDouble() * 900.0);
            product.setImageUrl("http://example.com/image" + i + ".jpg");
            product.setUnit("Unit " + i);
            product.setWeight(random.nextDouble() * 10);
            product.setAvailable(random.nextBoolean());
            product.setStatus(random.nextInt(2)); // 0 or 1 for status
            product.setCategory(generateRandomCategory()); // Danh mục cha ngẫu nhiên
            product.setDiscount(generateRandomDiscount()); // Discount ngẫu nhiên

            products.add(product);
        }

        return products;
    }

    // Tạo danh mục cha ngẫu nhiên
    private static Category generateRandomCategory() {
        Random random = new Random();
        String[] categoryNames = {"Fish", "Meat", "Vegetables", "Soft drinks", "Fruits", "Canned food", "Groceries"};
        int index = random.nextInt(categoryNames.length);
        return new Category(index + 1, categoryNames[index], new ParentCategory(index + 1, "Parent " + categoryNames[index], "http://example.com/parent" + index + ".jpg"));
    }

    // Tạo discount ngẫu nhiên (có thể không có)
    private static Discount generateRandomDiscount() {
        Random random = new Random();
        if (random.nextBoolean()) {
            int id = random.nextInt(100) + 1;
            return new Discount(id, "Discount " + id, random.nextInt(30));
        }
        return null;
    }
}
