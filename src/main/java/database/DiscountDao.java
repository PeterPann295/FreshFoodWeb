package database;

import model.Discount;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DiscountDao extends AbsDao<Discount> {
    @Override
    public int insert(Discount discount) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "insert into Discounts(name, percent) values (?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, discount.getName());
            pst.setInt(2, discount.getPercent());
            int i = pst.executeUpdate();
            if(i > 0){
                super.insert(discount);
            }
            JDBCUtil.closeConnection(con);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Discount> selectAll() {
        ArrayList<Discount> discount = new ArrayList<Discount>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Discounts";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int discountID = rs.getInt("id");
                String name = rs.getNString("name");
                int percent = rs.getInt("percent");
                Discount cate = new Discount(discountID,name,percent);
                discount.add(cate);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount;
    }
    public Discount selectById(int id) {
        Discount discount = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Discounts where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int discountID = rs.getInt("id");
                String name = rs.getNString("name");
                int percent = rs.getInt("percent");
                discount = new Discount(discountID,name,percent);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount;
    }
    public int update(Discount discount) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "update Discounts set name = ?, percent = ? where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, discount.getName());
            pst.setInt(2, discount.getPercent());
            pst.setInt(3, discount.getId());
            int i = pst.executeUpdate();
            if(i > 0){
                super.update(discount);
            }
            JDBCUtil.closeConnection(con);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Discount discount) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "delete from Discounts where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, discount.getId());
            int i = pst.executeUpdate();
            if(i > 0){
                super.delete(discount);
            }
            JDBCUtil.closeConnection(con);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DiscountDao discountDao = new DiscountDao();
        ArrayList<Discount> discounts = discountDao.selectAll();
        for (Discount d : discounts) {
            System.out.println(d);
        }
    }
}
