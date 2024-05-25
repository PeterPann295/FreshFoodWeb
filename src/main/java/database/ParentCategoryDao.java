package database;

import model.ParentCategory;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class ParentCategoryDao extends AbsDao<ParentCategory> {
    @Override
    public int insert(ParentCategory parentCategory) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "insert into ParentCategories( name , imageUrl) values (?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, parentCategory.getName());
            pst.setString(2, parentCategory.getImageURL());
            int  i = pst.executeUpdate();
            if(i > 0) {
                super.insert(parentCategory);
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(ParentCategory parentCategory) {
        return super.update(parentCategory);
    }

    @Override
    public int delete(ParentCategory parentCategory) {
        return super.delete(parentCategory);
    }

    @Override
    public ParentCategory selectById(int id) {
        ParentCategory parentCategory = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ParentCategories WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt("id");
                String name = rs.getNString("name");
                String url = rs.getNString("imageUrl");
                parentCategory = new ParentCategory(categoryID, name, url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentCategory;
    }


    @Override
    public ArrayList<ParentCategory> selectAll() {
        ArrayList<ParentCategory> categories = new ArrayList<ParentCategory>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select * from ParentCategories";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int categoryID = rs.getInt("id");
                String name = rs.getNString("name");
                String url = rs.getNString("imageUrl");
                ParentCategory cate = new ParentCategory(categoryID, name, url);
                categories.add(cate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static void main(String[] args) {
        ParentCategoryDao dao = new ParentCategoryDao();
        ParentCategory parentCategory = new ParentCategory("Test", "C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\assets\\images\\categories\\cá.jpg");
        System.out.println(dao.selectById(1));
    }
}
