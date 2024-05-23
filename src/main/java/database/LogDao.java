package database;

import model.Log;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class LogDao implements IDao<Log>{
    @Override
    public int insert(Log log) {
        try {
            System.out.println("da vao insert Log");
            Connection con = JDBCUtil.getConnection();
            String sql = "insert into logs (action, table_name , level, beforeData, afterData, user_id, time)  values (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, log.getAction());
            pst.setNString(2, log.getTable());
            pst.setNString(3, log.getLevel());
            pst.setNString(4, log.getBeforeData());
            pst.setNString(5, log.getAfterData());
            pst.setInt(6, log.getUser_id());
            pst.setTimestamp(7, log.getTime());
            int i = pst.executeUpdate();
            if (i > 0) {
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Log log) {
        return 0;
    }

    @Override
    public int delete(Log log) {
        return 0;
    }

    @Override
    public ArrayList<Log> selectAll() {
        return null;
    }

    @Override
    public Log selectById(int id) {
        return null;
    }
}
