package excel;

import database.ParentCategoryDao;
import model.ParentCategory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ParentCategoryExtractor {

    private static final Logger logger = Logger.getLogger(ParentCategoryExtractor.class.getName());

    public static List<ParentCategory> extractParentCategories(String imagePath) {
        List<ParentCategory> parentCategories = new ArrayList<>();

        // Danh sách các tệp ảnh trong thư mục categories
        File imageDir = new File(imagePath);
        File[] imageFiles = imageDir.listFiles();

        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                if (imageFile.isFile() && isImageFile(imageFile)) {
                    // Tạo danh mục cha từ tên tệp ảnh
                    String categoryName = extractCategoryName(imageFile.getName());
                    String imageURL = imageFile.getAbsolutePath();
                    ParentCategory parentCategory = new ParentCategory(categoryName, imageURL);
                    parentCategories.add(parentCategory);
                }
            }
        } else {
            logger.warning("Thư mục không chứa hình ảnh: " + imagePath);
        }

        return parentCategories;
    }

    // Kiểm tra xem tệp có phải là tệp ảnh không
    private static boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
    }

    // Trích xuất tên danh mục từ tên tệp ảnh
    private static String extractCategoryName(String fileName) {
        // Loại bỏ phần mở rộng của tên tệp ảnh (.png, .jpg, .jpeg)
        String categoryName = fileName.replaceFirst("[.][^.]+$", "");
        // Loại bỏ các ký tự không phải là chữ cái hoặc số
        categoryName = categoryName.replaceAll("[^a-zA-Z0-9]", " ");
        // Chuyển đổi thành chữ viết hoa và loại bỏ khoảng trắng ở đầu và cuối
        categoryName = categoryName.trim().toUpperCase();
        return categoryName;
    }

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
        List<ParentCategory> parentCategories = extractParentCategories(imagePath);

        importParentCategories(parentCategories);
    }
}
