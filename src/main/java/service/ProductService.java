package service;

import database.ProductDao;
import model.Product;
import utils.TrendProduct;

import java.util.ArrayList;

public class ProductService {
    private ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public int insert(Product product) {
        return productDao.insert(product);
    }

    public int update(Product product) {
        return productDao.update(product);
    }

    public int delete(Product product) {
        return productDao.delete(product);
    }

    public ArrayList<Product> selectAll() {
        return productDao.selectAll();
    }

    public Product selectById(int id) {
        return productDao.selectById(id);
    }

    public ArrayList<Product> selectNewestProducts() {
        return productDao.selectNewestProducts();
    }

    public ArrayList<Product> selectProductsByCategoryId(int categoryId) {
        return productDao.selectProductsByCategoryId(categoryId);
    }

    public ArrayList<Product> selectProductRelated(Product product) {
        return productDao.selectProductRealte(product);
    }

    public ArrayList<Product> selectProductByFilter(String[] categories, String priceRange, String discount, String sortBy) {
        return productDao.selectProductByFilter(categories, priceRange, discount, sortBy);
    }

    public ArrayList<Product> selectByNameProduct(String nameProduct) {
        return productDao.selectByNameProduct(nameProduct);
    }

    public ArrayList<Product> selectProductsByParentCategoryId(int parentId) {
        return productDao.selectProductsByParentCategoryId(parentId);
    }
    public int countProduct() {
        return productDao.countProduct();
    }
    public ArrayList<Product> selectPaging(int index) {
        return productDao.selectPaging(index);
    }
    public ArrayList<Product> selectProductHasDiscount() {
        return productDao.selectProductHasDiscount();
    }
    public ArrayList<TrendProduct> selectTrendProduct() {
        return productDao.selectTrendProduct();
    }
        public static void main(String[] args) {
        ProductService productService = new ProductService();

        // Test insert product
        Product newProduct = new Product();
        // Set properties for newProduct
        // newProduct.setName(...);
        // ...
        int result = productService.insert(newProduct);
        System.out.println("Insert Product Result: " + result);

        // Test get all products
        ArrayList<Product> products = productService.selectAll();
        System.out.println("All Products: " + products);

        // Test get product by id
        Product product = productService.selectById(1);
        System.out.println("Product with ID 1: " + product);

        // Test selectNewestProducts
        ArrayList<Product> newestProducts = productService.selectNewestProducts();
        System.out.println("Newest Products: " + newestProducts);

        // Test selectProductsByCategoryId
        ArrayList<Product> productsByCategory = productService.selectProductsByCategoryId(1);
        System.out.println("Products in Category 1: " + productsByCategory);

        // Test selectProductByFilter
        String[] categories = {"1", "2"};
        ArrayList<Product> filteredProducts = productService.selectProductByFilter(categories, "price between 50000 and 200000", null, "ZA");
        System.out.println("Filtered Products: " + filteredProducts);
    }
}
