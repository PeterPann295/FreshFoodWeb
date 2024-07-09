package service;

import database.CategoryDao;
import model.Category;

import java.util.ArrayList;

public class CategoryService {
    CategoryDao categoryDao;
    public CategoryService() {
        categoryDao = new CategoryDao();
    }
    public int insert(Category category) {
        return categoryDao.insert(category);
    }
    public ArrayList<Category> selectAll() {
        return categoryDao.selectAll();
    }
    public Category selectById(int id) {
        return categoryDao.selectById(id);
    }
    public ArrayList<Category> selectByParentId(int parentId) {
        return categoryDao.selectByParentId(parentId);
    }
    }
