package database;

import model.IModel;
import model.Log;

import java.util.ArrayList;

public class AbsDao <T extends IModel> implements IDao<T>{
    private LogDao logDao = new LogDao();
    protected static int user_id = 0;
    @Override
    public int insert(T t) {
        return logDao.insert(Log.insert(t, user_id));
    }

    @Override
    public int update(T t) {
        return logDao.insert(Log.update(t, user_id));
    }

    @Override
    public int delete(T t) {
        return logDao.insert(Log.delete(t, user_id));
    }

    @Override
    public ArrayList<T> selectAll() {
        return null;
    }

    @Override
    public T selectById(int id) {
        return null;
    }

    public int login(T model){
        return logDao.insert(Log.login(model, user_id));
    }
}
