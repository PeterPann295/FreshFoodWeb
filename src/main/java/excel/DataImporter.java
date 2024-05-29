package excel;

import database.CategoryDao;
import database.ParentCategoryDao;
import model.Category;
import model.ParentCategory;

import java.util.ArrayList;
import java.util.List;

public class DataImporter {

    private static void importParentCategories(List<ParentCategory> parentCategories) {
        ParentCategoryDao parentCategoryDao = new ParentCategoryDao();

        for (ParentCategory parentCategory : parentCategories) {
            int parentCategoryId = parentCategoryDao.insert(parentCategory);
            if (parentCategoryId > 0) {
                System.out.println("Đã thêm ParentCategory: " + parentCategory.getName());
            } else {
                System.out.println("Không thể thêm ParentCategory: " + parentCategory.getName());
            }
        }
    }




    public static void main(String[] args) {
        String imagePath = "C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\assets\\images\\categories";
        List<ParentCategory> parentCategories = ParentCategoryExtractor.extractParentCategories(imagePath);
        // Thêm dữ liệu vào cơ sở dữ liệu vào bảng ParentCategory
        DataImporter.importParentCategories(parentCategories);


    }
}
