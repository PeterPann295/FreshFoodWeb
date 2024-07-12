package database;

import model.Category;
import model.Discount;
import model.Product;
import utils.JDBCUtil;
import utils.TrendProduct;

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
    public int update(Product product) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE products SET name = ?, description = ?, price = ?, imageUrl = ?, unit = ?, weight = ?, available = ?, category_id = ?, discount_id = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);

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
            pst.setInt(10, product.getId());

            int i = pst.executeUpdate();
            if(i > 0) {
                super.update(product);
            }
            JDBCUtil.closeConnection(con);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Product product) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "delete from products where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, product.getId());
            int i = pst.executeUpdate();
            if(i > 0) {
                super.delete(product);
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
    public ArrayList<Product> selectProductsByCategoryId(int category_id) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products where category_id = ? ORDER BY id DESC LIMIT 5"; // Sắp xếp giảm dần theo ID và giới hạn kết quả là 10
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, category_id);
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
    public ArrayList<Product> selectProductRealte(Product product) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products where category_id = ? and id <> ? ORDER BY id DESC LIMIT 5"; // Sắp xếp giảm dần theo ID và giới hạn kết quả là 10
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, product.getCategory().getId());
            pst.setInt(2, product.getId());
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
                Product p = new Product(id, name, description, price, imageUrl, unit, weight, available, category, discount);
                products.add(p);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    public ArrayList<Product> selectProductByFilter(String[] categories, String priceRange, String discount, String sortBy) {
        ArrayList<Product> products = new ArrayList<>();

        boolean hasCategoryFilter = categories != null && categories.length > 0;
        boolean hasPriceFilter = priceRange != null;
        boolean hasDiscountFilter = discount != null;
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM products ");
        if(hasCategoryFilter || hasPriceFilter || hasDiscountFilter){
            queryBuilder = new StringBuilder("SELECT * FROM products WHERE ");
        }
        if (hasCategoryFilter) {
            queryBuilder.append("(");
            for (int i = 0; i < categories.length; i++) {
                queryBuilder.append("category_id = ?");
                if (i < categories.length - 1) {
                    queryBuilder.append(" OR ");
                }
            }
            queryBuilder.append(")");
        }

        if (hasPriceFilter) {
            if (hasCategoryFilter) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(priceRange);
        }

        if (hasDiscountFilter) {
            if (hasCategoryFilter || hasPriceFilter) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append("discount_id is not null");
        }
        if (sortBy != null) {
            queryBuilder.append(" ORDER BY ");
            switch (sortBy) {
                case "giaGiam":
                    queryBuilder.append("price DESC");
                    break;
                case "giaTang":
                    queryBuilder.append("price ASC");
                    break;
                case "AZ":
                    queryBuilder.append("name ASC");
                    break;
                case "ZA":
                    queryBuilder.append("name DESC");
                    break;
                default:
                    break;
            }
        }
        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(queryBuilder.toString());
            int paramIndex = 1;

            if (hasCategoryFilter) {
                for (String categoryId : categories) {
                    pst.setInt(paramIndex++, Integer.parseInt(categoryId));
                }
            }
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
                Discount dis = discountDao.selectById(discountId);
                Product p = new Product(id, name, description, price, imageUrl, unit, weight, available, category, dis);
                products.add(p);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    public ArrayList<Product> selectByNameProduct(String nameProduct) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products WHERE name like ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" +nameProduct + "%");
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
    public ArrayList<Product> selectProductsByParentCategoryId(int parentId) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT p.*\n" +
                    "FROM products p\n" +
                    "JOIN categories c ON p.category_id = c.id\n" +
                    "JOIN parentcategories pc ON c.id_parent = pc.id\n" +
                    "WHERE pc.id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, parentId);
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
    public int countProduct(){
        int count = 0;
        String sql = "SELECT COUNT(*) FROM products";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public ArrayList<Product> selectPaging(int index) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products ORDER BY id \n" +
                    "LIMIT 12 OFFSET ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, (index-1)*12);
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
    public ArrayList<Product> selectProductHasDiscount() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products where discount_id IS NOT NULL ORDER BY id DESC LIMIT 10"; // Sắp xếp giảm dần theo ID và giới hạn kết quả là 10
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
    public ArrayList<TrendProduct> selectTrendProduct() {
        ArrayList<TrendProduct> products = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT \n" +
                    "    p.id AS product_id,\n" +
                    "    SUM(CASE WHEN DATE_FORMAT(o.time_order, '%Y-%m') = DATE_FORMAT(CURRENT_DATE - INTERVAL 2 MONTH, '%Y-%m') THEN oi.quantity ELSE 0 END) AS quantity_month_1,\n" +
                    "    SUM(CASE WHEN DATE_FORMAT(o.time_order, '%Y-%m') = DATE_FORMAT(CURRENT_DATE - INTERVAL 1 MONTH, '%Y-%m') THEN oi.quantity ELSE 0 END) AS quantity_month_2,\n" +
                    "    SUM(CASE WHEN DATE_FORMAT(o.time_order, '%Y-%m') = DATE_FORMAT(CURRENT_DATE, '%Y-%m') THEN oi.quantity ELSE 0 END) AS quantity_month_3\n" +
                    "FROM \n" +
                    "    orders o\n" +
                    "JOIN \n" +
                    "    order_items oi ON o.id = oi.order_id\n" +
                    "JOIN \n" +
                    "    products p ON oi.product_id = p.id\n" +
                    "WHERE \n" +
                    "    o.time_order >= DATE_SUB(CURRENT_DATE, INTERVAL 2 MONTH)\n" +
                    "    AND o.status_id = 4\n" +
                    "GROUP BY \n" +
                    "    p.name\n" +
                    "ORDER BY \n" +
                    "    p.name;"; // Sắp xếp giảm dần theo ID và giới hạn kết quả là 10
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int qm1 = rs.getInt("quantity_month_1");
                int qm2 = rs.getInt("quantity_month_2");
                int qm3 = rs.getInt("quantity_month_3");
                Product product = selectById(productId);
                products.add(new TrendProduct(selectById(productId), qm1, qm2, qm3));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }



    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        System.out.println(productDao.selectTrendProduct().size());
    }


}