package database;

import model.Category;
import model.Discount;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDao extends AbsDao<Category> {
    @Override
    public int insert(Category category) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "insert into categories(name, id_parent) values (?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, category.getName());
            pst.setInt(2, category.getParentCategory().getId());
            int i = pst.executeUpdate();
            if(i > 0){
                super.insert(category);
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
            JDBCUtil.closeConnection(con);
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
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
    public ArrayList<Category> selectByParentId(int parentId) {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from Categories where  id_parent = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, parentId);
            ResultSet rs = pst.executeQuery();
            ParentCategoryDao dao = new ParentCategoryDao();
            while(rs.next()) {
                int id_cate = rs.getInt("id");
                String name = rs.getNString("name");
                int id_parent = rs.getInt("id_parent");
                Category c = new Category(id_cate, name, dao.selectById(id_parent));
                categories.add(c);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}