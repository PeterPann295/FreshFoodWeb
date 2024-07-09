package utils;

import model.Order;
import model.OrderItem;

public class StringFilter {
    public static String concatenateWithOR(String[] stringArray) {
        // Kiểm tra nếu mảng rỗng
        if (stringArray == null || stringArray.length == 0) {
            return null;
        }

        // Sử dụng StringBuilder để xây dựng chuỗi kết quả
        StringBuilder result = new StringBuilder(stringArray[0]);

        // Duyệt qua từng phần tử trong mảng (bắt đầu từ index 1)
        for (int i = 1; i < stringArray.length; i++) {
            // Thêm chuỗi " OR " và phần tử tiếp theo vào chuỗi kết quả
            result.append(" OR ").append(stringArray[i]);
        }

        // Chuyển đổi StringBuilder thành chuỗi và trả về
        return result.toString();
    }

    public static String queryFilter(String parentCategoryID, String priceFilter) {
        String result = "";
        if (parentCategoryID == null) {
            result = priceFilter;
        } else if (priceFilter == null) {
            result = "Categories.patentCategoryID=" + "'" + parentCategoryID + "'";
        } else {
            result = "Categories.patentCategoryID=" + parentCategoryID + " AND " + priceFilter;
        }
        return result;
    }

    public static String xacThucDonHang(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Chi tiết đơn hàng :" + "\n");
        for (OrderItem o : order.getOrderItems()) {
            sb.append(o.getProduct().getName() + " x" + o.getQuantity() + ", ");
        }
        sb.append(". Tổng số tiền thanh toán: " + order.getTotal() + ".");
        return sb.toString();

    }

    public static String maskEmail(String email) {
        {
            int atIndex = email.indexOf('@');
            String username = email.substring(0, atIndex);
            String domain = email.substring(atIndex);

            // Mask the username
            StringBuilder maskedUsername = new StringBuilder();
            for (int i = 0; i < username.length(); i++) {
                if(i < 5) {
                    maskedUsername.append(username.charAt(i));
                    continue;
                }
                if (i < username.length() - 5) {
                    maskedUsername.append('*');
                } else {
                    maskedUsername.append(username.charAt(i));
                }
            }

            // Combine masked username and domain
            return maskedUsername.toString() + domain;

        }
    }
    public static void main(String[] args) {
        System.out.println(maskEmail("leminhhieu.ltp2021@gmail.com"));
    }
}