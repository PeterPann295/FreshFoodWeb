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
        return 0;
    }

    @Override
    public int update(Voucher voucher) {
        return 0;
    }

    @Override
    public int delete(Voucher voucher) {
        return 0;
    }

    @Override
    public ArrayList<Voucher> selectAll() {
        return null;
    }

    @Override
    public Voucher selectById(int id) {
        Voucher voucher = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from voucher where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int id_pm = rs.getInt("id");
                String name = rs.getNString("name");
                double discount = rs.getDouble("discount");
                voucher = new Voucher(id_pm, name, discount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voucher;
    }
}
