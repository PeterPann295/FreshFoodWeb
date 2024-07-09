package database;

import model.OrderStatus;
import model.Voucher;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VoucherDao implements IDao<Voucher> {
    @Override
    public int insert(Voucher voucher) {
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "insert into vouchers (code, discount) values (?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, voucher.getCode());
            pst.setDouble(2, voucher.getDiscount());
            int i = pst.executeUpdate();
            if(i > 0) {
                JDBCUtil.closeConnection(con);
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Voucher voucher) {
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "update vouchers set code = ?, discount = ? where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, voucher.getCode());
            pst.setDouble(2, voucher.getDiscount());
            pst.setInt(3, voucher.getId());
            int i = pst.executeUpdate();
            if(i > 0) {
                JDBCUtil.closeConnection(con);
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Voucher voucher) {
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "delete from vouchers where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, voucher.getId());
            int i = pst.executeUpdate();
            if(i > 0) {
                JDBCUtil.closeConnection(con);
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Voucher> selectAll() {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from vouchers";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getNString("code");
                double discount = rs.getDouble("discount");
                Voucher voucher = new Voucher(id, code, discount);
                vouchers.add(voucher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vouchers;
    }

    @Override
    public Voucher selectById(int id) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM vouchers WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Voucher voucher = new Voucher(rs.getInt("id"), rs.getString("code"), rs.getDouble("discount"));
                JDBCUtil.closeConnection(con);
                return voucher;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Voucher selectByCode(String code) {
        Voucher voucher = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from vouchers where code = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, code);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int id_pm = rs.getInt("id");
                String name = rs.getNString("code");
                double discount = rs.getDouble("discount");
                voucher = new Voucher(id_pm, name, discount);
            }
            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return voucher;
    }

    public static void main(String[] args) {
        Voucher voucher = new Voucher("gg20k", 20000);
        VoucherDao voucherDao = new VoucherDao();
        System.out.println( voucherDao.insert(voucher));
        //System.out.println(voucherDao.update(voucher));

        //System.out.println(voucherDao.selectByCode("gg30k"));

    }
}
