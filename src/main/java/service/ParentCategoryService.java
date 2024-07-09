package service;

import database.ParentCategoryDao;
import model.ParentCategory;

import java.util.ArrayList;

public class ParentCategoryService {
    private ParentCategoryDao parentCategoryDao;

    public ParentCategoryService() {
        this.parentCategoryDao = new ParentCategoryDao();
    }

    public int insert(ParentCategory parentCategory) {
        return parentCategoryDao.insert(parentCategory);
    }

    public int update(ParentCategory parentCategory) {
        return parentCategoryDao.update(parentCategory);
    }

    public int delete(ParentCategory parentCategory) {
        return parentCategoryDao.delete(parentCategory);
    }

    public ArrayList<ParentCategory> selectAll() {
        return parentCategoryDao.selectAll();
    }

    public ParentCategory selectById(int id) {
        return parentCategoryDao.selectById(id);
    }

}
