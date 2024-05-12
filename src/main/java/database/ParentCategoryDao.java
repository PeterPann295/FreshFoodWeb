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
        return super.selectById(id);
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
        System.out.println(dao.selectAll().size());
    }
}
