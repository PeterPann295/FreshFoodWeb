package database;

import model.Log;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
            JDBCUtil.closeConnection(con);
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

    public int deleteById(int id) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "delete from logs where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            int i = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            if (i > 0) {
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public ArrayList<Log> selectAll() {
        ArrayList<Log> logs = new ArrayList<Log>();
        try {
           Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM logs";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("id"));
                log.setAction(rs.getNString("action"));
                log.setTable(rs.getNString("table_name"));
                log.setLevel(rs.getNString("level"));
                log.setBeforeData(rs.getNString("beforeData"));
                log.setAfterData(rs.getNString("afterData"));
                log.setUsername(rs.getInt("user_id"));
                log.setTime(rs.getTimestamp("time"));
                logs.add(log);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return logs;
    }

    @Override
    public Log selectById(int id) {
        Log log = new Log();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM logs where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                log.setId(rs.getInt("id"));
                log.setAction(rs.getNString("action"));
                log.setTable(rs.getNString("table_name"));
                log.setLevel(rs.getNString("level"));
                log.setBeforeData(rs.getNString("beforeData"));
                log.setAfterData(rs.getNString("afterData"));
                log.setUsername(rs.getInt("user_id"));
                log.setTime(rs.getTimestamp("time"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return log;
    }

    public static void main(String[] args) {
        LogDao logDao = new LogDao();
        System.out.println(logDao.selectAll().size());
    }
}
