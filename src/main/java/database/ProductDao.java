package database;

import model.Category;
import model.Discount;
import model.Product;
import utils.JDBCUtil;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

public class ProductDao extends AbsDao<Product> {
    @Override
    public int insert(Product product) {
        try {
            var con = JDBCUtil.getConnection();
            String sql = "INSERT INTO products(name, description, price, imageUrl, unit, weight, available, category_id, discount_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, product.getName());
            pst.setString(2, product.getDescription());
            pst.setDouble(3, product.getPrice());
            pst.setString(4, product.getImageUrl());
            pst.setString(5, product.getUnit());
            pst.setDouble(6, product.getWeight());
            pst.setBoolean(7, product.isAvailable());
            pst.setInt(8, product.getCategory().getId());
            if(product.getDiscount() != null) {
            pst.setInt(9, product.getDiscount().getId());
            } else {
                pst.setNull(9, Types.INTEGER);
            }
            int i = pst.executeUpdate();
            if (i > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    product.setId(generatedId);
                }
                super.insert(product);
            }
            JDBCUtil.closeConnection(con);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Product> selectAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String imageUrl = rs.getString("imageUrl");
                String unit = rs.getString("unit");
                double weight = rs.getDouble("weight");
                boolean available = rs.getBoolean("available");
                int categoryId = rs.getInt("category_id");
                int discountId = rs.getInt("discount_id");

                CategoryDao categoryDao = new CategoryDao();
                Category category = categoryDao.selectById(categoryId);

                DiscountDao discountDao = new DiscountDao();
                Discount discount = discountDao.selectById(discountId);

                Product product = new Product(id, name, description, price, imageUrl, unit, weight, available, category, discount);
                products.add(product);
            }
            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    @Override
    public Product selectById(int id) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String imageUrl = rs.getString("imageUrl");
                String unit = rs.getString("unit");
                double weight = rs.getDouble("weight");
                boolean available = rs.getBoolean("available");
                int categoryId = rs.getInt("category_id");
                int discountId = rs.getInt("discount_id");

                CategoryDao categoryDao = new CategoryDao();
                Category category = categoryDao.selectById(categoryId);

                DiscountDao discountDao = new DiscountDao();
                Discount discount = discountDao.selectById(discountId);

                return new Product(id, name, description, price, imageUrl, unit, weight, available, category, discount);
            }
            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Product> selectNewestProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products ORDER BY id DESC LIMIT 10"; // Sắp xếp giảm dần theo ID và giới hạn kết quả là 10
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String imageUrl = rs.getString("imageUrl");
                String unit = rs.getString("unit");
                double weight = rs.getDouble("weight");
                boolean available = rs.getBoolean("available");
                int categoryId = rs.getInt("category_id");
                int discountId = rs.getInt("discount_id");

                CategoryDao categoryDao = new CategoryDao();
                Category category = categoryDao.selectById(categoryId);

                DiscountDao discountDao = new DiscountDao();
                Discount discount = discountDao.selectById(discountId);

                Product product = new Product(id, name, description, price, imageUrl, unit, weight, available, category, discount);
                products.add(product);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        ArrayList<Product> products = productDao.selectNewestProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }


}
