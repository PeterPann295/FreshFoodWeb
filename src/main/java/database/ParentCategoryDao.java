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
        int newParentCategoryId = 0;

        try {
            Connection con = JDBCUtil.getConnection();

            // Truy vấn để tìm ID lớn nhất hiện có trong bảng ParentCategories
            String getMaxIdQuery = "SELECT MAX(id) AS maxId FROM ParentCategories";
            PreparedStatement getMaxIdStatement = con.prepareStatement(getMaxIdQuery);
            ResultSet resultSet = getMaxIdStatement.executeQuery();

            // Lấy ID lớn nhất
            if (resultSet.next()) {
                int maxId = resultSet.getInt("maxId");
                newParentCategoryId = maxId + 1; // Tạo ID mới bằng cách tăng thêm 1
            }

            // Chèn dữ liệu vào bảng ParentCategories với ID mới
            String sql = "insert into ParentCategories( id,name , imageUrl) values (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, newParentCategoryId);
            pst.setString(2, parentCategory.getName());
            pst.setString(3, parentCategory.getImageURL());
            int  i = pst.executeUpdate();
            if(i > 0) {
                super.insert(parentCategory);
            }
            JDBCUtil.closeConnection(con);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newParentCategoryId;
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
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static void main(String[] args) {
        ParentCategoryDao dao = new ParentCategoryDao();
        ParentCategory parentCategory = new ParentCategory("ParentCategory 1", "https://www.google.com");
        int newParentCategoryId = dao.insert(parentCategory);
        if (newParentCategoryId > 0) {
            System.out.println("Đã thêm ParentCategory: " + parentCategory.getName());
        } else {
            System.out.println("Không thể thêm ParentCategory: " + parentCategory.getName());
        }
    }

    public ParentCategory getParentCategoryByName(String name) {
        ParentCategory parentCategory = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ParentCategories WHERE name = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt("id");
                String categoryName = rs.getNString("name");
                String imageUrl = rs.getNString("imageUrl");
                parentCategory = new ParentCategory(categoryID, categoryName, imageUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentCategory;
    }
}
