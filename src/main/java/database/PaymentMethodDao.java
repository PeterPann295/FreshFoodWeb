package database;

import model.PaymentMethod;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentMethodDao implements IDao<PaymentMethod>{
    @Override
    public int insert(PaymentMethod paymentMethod) {
        return 0;
    }

    @Override
    public int update(PaymentMethod paymentMethod) {
        return 0;
    }

    @Override
    public int delete(PaymentMethod paymentMethod) {
        return 0;
    }

    @Override
    public ArrayList<PaymentMethod> selectAll() {
        ArrayList<PaymentMethod> payments = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from payment_methods";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int id_pm = rs.getInt("id");
                String name = rs.getNString("name");
                payments.add(new PaymentMethod(id_pm, name));
            }
            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payments;
    }

    @Override
    public PaymentMethod selectById(int id) {
        PaymentMethod paymentMethod = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from payment_methods where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int id_pm = rs.getInt("id");
                String name = rs.getNString("name");
                paymentMethod = new PaymentMethod(id, name);
            }
            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentMethod;
    }

    public static void main(String[] args) {
        PaymentMethodDao dao = new PaymentMethodDao();
        System.out.println(dao.selectAll().size());
    }
}
