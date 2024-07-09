package service;

import database.ImportProductDao;
import model.ImportProduct;

import java.util.ArrayList;

public class ImportProductService {
    private ImportProductDao importProductDao;

    public ImportProductService() {
        this.importProductDao = new ImportProductDao();
    }

    public int insert(ImportProduct importProduct) {
        return importProductDao.insert(importProduct);
    }

    public int update(ImportProduct importProduct) {
        return importProductDao.update(importProduct);
    }

    public int delete(ImportProduct importProduct) {
        return importProductDao.delete(importProduct);
    }

    public ArrayList<ImportProduct> selectAll() {
        return importProductDao.selectAll();
    }

    public ImportProduct selectById(int id) {
        return importProductDao.selectById(id);
    }

    public int selectTotalQuantityImportByProductId(int productId) {
        return importProductDao.selectTotalQuatityImportByProductId(productId);
    }

    public int selectTotalProductInStock(int productId) {
        return importProductDao.selectToTalProductInStock(productId);
    }

    public static void main(String[] args) {
        ImportProductService importProductService = new ImportProductService();

        // Test insert importProduct
        ImportProduct newImportProduct = new ImportProduct();
        // Set properties for newImportProduct
        int result = importProductService.insert(newImportProduct);
        System.out.println("Insert ImportProduct Result: " + result);

        // Test get all importProducts
        ArrayList<ImportProduct> importProducts = importProductService.selectAll();
        System.out.println("All ImportProducts: " + importProducts);

        // Test get importProduct by id
        ImportProduct importProduct = importProductService.selectById(1);
        System.out.println("ImportProduct with ID 1: " + importProduct);

        // Test get total quantity import by product id
        int totalQuantity = importProductService.selectTotalQuantityImportByProductId(7);
        System.out.println("Total Quantity Import By Product ID 7: " + totalQuantity);

        // Test get total product in stock by product id
        int totalInStock = importProductService.selectTotalProductInStock(7);
        System.out.println("Total Product In Stock By Product ID 7: " + totalInStock);
    }
}
