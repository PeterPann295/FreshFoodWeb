package database;

import java.util.ArrayList;

public interface IDao <T>{
    int insert(T t);
    int update(T t);
    int delete(T t);
    ArrayList<T> selectAll();
    T selectById(int id);
}
