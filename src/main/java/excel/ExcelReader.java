package excel;
import database.CategoryDao;
import database.DiscountDao;
import model.Category;
import model.Discount;
import model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
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
        int categoryId = getIntCellValue(row.getCell(8));
        int discountId = getIntCellValue(row.getCell(9));

        Category category = new CategoryDao().selectById(categoryId);
        Discount discount = new DiscountDao().selectById(discountId);


        // Tạo đối tượng Product và thêm vào danh sách sản phẩm
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
        product.setDiscount(discount);

        return product;

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

        System.out.println("Danh sách sản phẩm đọc từ file Excel:" + products);
    }
}
