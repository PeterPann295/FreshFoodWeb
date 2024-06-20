package database;

import model.Discount;
import model.ImportProduct;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ImportProductDao implements IDao<ImportProduct>{

    @Override
    public int insert(ImportProduct importProduct) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "insert into import_products(product_id, quatity, date, customer_id) values (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, importProduct.getProduct().getId());
            pst.setInt(2, importProduct.getQuatity());
            pst.setTimestamp(3, importProduct.getDate());
            pst.setInt(4, importProduct.getCustomer().getId());
            int i = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(ImportProduct importProduct) {
        return 0;
    }

    @Override
    public int delete(ImportProduct importProduct) {
        return 0;
    }

    @Override
    public ArrayList<ImportProduct> selectAll() {
        ArrayList<ImportProduct> importProducts = new ArrayList<ImportProduct>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from import_products";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ProductDao productDao = new ProductDao();
            CustomerDao customerDao = new CustomerDao();
            while(rs.next()) {
                int id = rs.getInt("id");
                int productId = rs.getInt("product_id");
                int quatity = rs.getInt("quatity");
                Timestamp date = rs.getTimestamp("date");
                int customerId = rs.getInt("customer_id");
                ImportProduct ip = new ImportProduct(id,productDao.selectById(productId), quatity, date, customerDao.selectById(customerId));
                importProducts.add(ip);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importProducts;
    }
    public int selectTotalQuatityImportByProductId(int productId){
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT SUM(quatity) AS total_quatity\n" +
                    "FROM import_products WHERE product_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, productId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getInt("total_quatity");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int selectToTalProductInStock(int productId){
        OrderDao orderDao = new OrderDao();
        return selectTotalQuatityImportByProductId(productId) - orderDao.selectTotalProductSold(productId);
    }
    @Override
    public ImportProduct selectById(int id) {
        return null;
    }

    public static void main(String[] args) {
        ImportProductDao importProductDao = new ImportProductDao();
        System.out.println(importProductDao.selectTotalQuatityImportByProductId(7));
    }
}
