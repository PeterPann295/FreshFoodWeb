package database;

import model.Category;
import model.Discount;
import model.ParentCategory;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends AbsDao<Category> {
    @Override
    public int insert(Category category) {
        int newCategoryId = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String getMaxIdQuery = "SELECT MAX(id) AS maxId FROM Categories";
            PreparedStatement getMaxIdStatement = con.prepareStatement(getMaxIdQuery);
            ResultSet resultSet = getMaxIdStatement.executeQuery();
            if (resultSet.next()) {
                int maxId = resultSet.getInt("maxId");
                newCategoryId = maxId + 1;
            }
            String sql = "insert into Categories( id,name , id_parent) values (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, newCategoryId);
            pst.setString(2, category.getName());
            pst.setInt(3, category.getParentCategory().getId());
            int  i = pst.executeUpdate();
            if(i > 0) {
                super.insert(category);
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCategoryId;
    }
    @Override
    public ArrayList<Category> selectAll() {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Categories";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ParentCategoryDao dao = new ParentCategoryDao();
            while(rs.next()) {
                int id= rs.getInt("id");
                String name = rs.getNString("name");
                int id_parent = rs.getInt("id_parent");
                Category category = new Category(id, name, dao.selectById(id_parent));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
    @Override
    public Category selectById(int id) {
        Category category = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Categories where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            ParentCategoryDao dao = new ParentCategoryDao();
            while(rs.next()) {
                int id_cate = rs.getInt("id");
                String name = rs.getNString("name");
                int id_parent = rs.getInt("id_parent");
                category = new Category(id_cate, name, dao.selectById(id_parent));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public Category getCategoryByName(String name) {
        Category category = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Categories WHERE name = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, name);
            ResultSet rs = pst.executeQuery();
            ParentCategoryDao dao = new ParentCategoryDao();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nameCate = rs.getNString("name");
                int id_parent = rs.getInt("id_parent");
                category = new Category(id, nameCate, dao.selectById(id_parent));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public static void main(String[] args) {
        CategoryDao dao = new CategoryDao();
        ParentCategoryDao parentCategoryDao = new ParentCategoryDao();

        // Lấy danh sách tất cả các danh mục cha từ cơ sở dữ liệu
        List<ParentCategory> parentCategories = parentCategoryDao.selectAll();

        // Lặp qua danh sách các danh mục cha và tạo các danh mục con tương ứng
        for (ParentCategory parentCategory : parentCategories) {
            // Lấy tên của danh mục cha
            String parentCategoryName = parentCategory.getName();

            // Tạo đối tượng Category với tên của danh mục cha
            Category category = new Category(parentCategoryName, parentCategory);

            // Thêm category vào cơ sở dữ liệu
            dao.insert(category);
        }
    }



}
