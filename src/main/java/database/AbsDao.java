package database;

import model.IModel;
import model.Log;

import java.util.ArrayList;

public class AbsDao <T extends IModel> implements IDao<T>{
    private LogDao logDao = new LogDao();
    protected static String username = "";
    @Override
    public int insert(T t) {
        return logDao.insert(Log.insert(t, username));
    }

    @Override
    public int update(T t) {
        return logDao.insert(Log.update(t, username));
    }

    @Override
    public int delete(T t) {
        return logDao.insert(Log.delete(t, username));
    }

    @Override
    public ArrayList<T> selectAll() {
        return null;
    }

    @Override
    public T selectById(int id) {
        return null;
    }
}
