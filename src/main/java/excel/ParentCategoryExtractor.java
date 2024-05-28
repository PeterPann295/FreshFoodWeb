package excel;

import model.ParentCategory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentCategoryExtractor {

    public static List<ParentCategory> extractParentCategories(String imagePath) {
        List<ParentCategory> parentCategories = new ArrayList<>();
        Map<String, String> categoryMap = new HashMap<>();

        // Ánh xạ tên danh mục cha với tên tệp ảnh
        categoryMap.put("Cá", "ca.png");
        categoryMap.put("Thịt", "thit.png");
        categoryMap.put("Rau", "rau.png");
        categoryMap.put("Nước ngọt", "nuocngot.png");
        categoryMap.put("Trái cây", "traicay.png");
        categoryMap.put("Đồ hộp", "dohop.png");
        categoryMap.put("Thực phẩm", "thucpham.png");

        File imageDir = new File(imagePath);
        if (imageDir.isDirectory()) {
            File[] imageFiles = imageDir.listFiles();
            if (imageFiles != null) {
                for (Map.Entry<String, String> entry : categoryMap.entrySet()) {
                    String categoryName = entry.getKey();
                    String imageName = entry.getValue();

                    for (File imageFile : imageFiles) {
                        if (imageFile.isFile() && imageFile.getName().equals(imageName)) {
                            String imageURL = imageFile.getAbsolutePath();
                            ParentCategory parentCategory = new ParentCategory(categoryName, imageURL);
                            parentCategories.add(parentCategory);
                        }
                    }
                }
            }
        }

        return parentCategories;
    }

    public static void main(String[] args) {
        String imagePath = "C:\\IntelliJ\\FreshFoodWeb\\src\\main\\webapp\\assets\\images\\categories";
        List<ParentCategory> parentCategories = extractParentCategories(imagePath);
        for (ParentCategory parentCategory : parentCategories) {
            System.out.println(parentCategory.getName() + " - " + parentCategory.getImageURL());
        }
    }
}
