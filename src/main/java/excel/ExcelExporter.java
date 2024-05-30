package excel;

import database.ParentCategoryDao;
import model.Category;
import model.Discount;
import model.ParentCategory;
import model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelExporter {

    private static final Logger logger = Logger.getLogger(ExcelExporter.class.getName());

    public void exportProductsToExcel(List<Product> products, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");

        // Tạo tiêu đề
        String[] columnHeaders = {
                "ID", "Name", "Description", "Price", "Image URL", "Unit",
                "Weight", "Available",  "Category", "Discount"
        };

        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(i);
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
            row.createCell(8).setCellValue(product.getCategory().getName());
            String discountName = product.getDiscount() != null ? product.getDiscount().getName() : "";
            row.createCell(9).setCellValue(discountName);
        }

        // Ghi file Excel ra ổ đĩa
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Lỗi khi ghi file Excel", e);
            throw e;
        } finally {
            workbook.close();
        }

        logger.info("File Excel được tạo thành công tại " + filePath);
    }

    public static void main(String[] args) {
        String filePath = "C:\\IntelliJ\\FreshFoodWeb\\products.xlsx";
        try {
            // Lấy danh sách danh mục cha từ cơ sở dữ liệu
            ParentCategoryDao parentCategoryDao = new ParentCategoryDao();
            List<ParentCategory> parentCategories = parentCategoryDao.selectAll();

            // Tạo danh sách sản phẩm mẫu với mỗi danh mục cha có 10 sản phẩm
            List<Product> sampleProducts = generateSampleProducts(70, parentCategories);

            // Xuất danh sách sản phẩm ra file Excel
            new ExcelExporter().exportProductsToExcel(sampleProducts, filePath);

            System.out.println("File Excel được tạo thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tạo danh sách sản phẩm mẫu với các danh mục cha khác nhau
    public static List<Product> generateSampleProducts(int numberOfProducts, List<ParentCategory> parentCategories) {
        List<Product> products = new ArrayList<>();
        Random random = new Random();
        String imagePath = "C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\assets\\images\\products";
        Category category = new Category();
        int productId = 1;

        for (ParentCategory parentCategory : parentCategories) {
            File categoryFolder = new File(imagePath + File.separator + parentCategory.getName());
            File[] imageFiles = categoryFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));

            if (imageFiles != null && imageFiles.length > 0) {
                for (int i = 0; i < numberOfProducts / parentCategories.size(); i++) {
                    int randomIndex = random.nextInt(imageFiles.length);
                    File imageFile = imageFiles[randomIndex];

                    Product product = new Product();
                    product.setId(productId++);
                    product.setName("Product " + productId);
                    product.setDescription("Description for product " + productId);
                    product.setPrice(100.0 + random.nextDouble() * 900.0);
                    product.setImageUrl(imageFile.getAbsolutePath());
                    product.setUnit("Unit " + productId);
                    product.setWeight(random.nextDouble() * 10);
                    product.setAvailable(random.nextBoolean());
                    category.setId(parentCategory.getId());
                    product.setDiscount(generateRandomDiscount());

                    products.add(product);
                }
            } else {
                logger.warning("Không có ảnh sản phẩm trong thư mục: " + categoryFolder.getAbsolutePath());
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
