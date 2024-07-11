package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiGHN {
    public static DataRespone getData(String addressDetail, String wardId, String districtId) {
        DataRespone dataRespone = new DataRespone();
        String apiUrl = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create";
        String token = "80117275-f321-11ee-8bfa-8a2dda8ec551";
        String shopId = "0345778312-191687";

        // JSON data to send in the POST request
        String jsonData = "{\n" +
                "    \"payment_type_id\": 2,\n" +
                "    \"note\": \"Tintest 123\",\n" +
                "    \"required_note\": \"KHONGCHOXEMHANG\",\n" +
                "    \"from_name\": \"TinTest124\",\n" +
                "    \"from_phone\": \"0345778312\",\n" +
                "    \"from_address\": \"Khu Phố 6, Phường Linh Trung, Quận Thủ Đưc, Hồ Chí Minh, Vietnam\",\n" +
                "    \"from_ward_name\": \"Phường Linh Trung\",\n" +
                "    \"from_district_name\": \"Quận Thủ Đức\",\n" +
                "    \"from_province_name\": \"HCM\",\n" +
                "    \"return_phone\": \"0332190444\",\n" +
                "    \"return_address\": \"39 NTT\",\n" +
                "    \"return_district_id\": null,\n" +
                "    \"return_ward_code\": \"\",\n" +
                "    \"client_order_code\": \"\",\n" +
                "    \"to_name\": \"TinTest124\",\n" +
                "    \"to_phone\": \"0987654321\",\n" +
                "    \"to_address\": \"" + addressDetail + "\",\n" +
                "    \"to_ward_code\": \"" + wardId + "\",\n" +
                "    \"to_district_id\": " + districtId + ",\n" +
                "    \"cod_amount\": 500000,\n" +
                "    \"content\": \"Theo New York Times\",\n" +
                "    \"weight\": 5000,\n" +
                "    \"length\": 1,\n" +
                "    \"width\": 19,\n" +
                "    \"height\": 10,\n" +
                "    \"pick_station_id\": 1444,\n" +
                "    \"deliver_station_id\": null,\n" +
                "    \"insurance_value\": 1000000,\n" +
                "    \"service_id\": 0,\n" +
                "    \"service_type_id\": 2,\n" +
                "    \"coupon\": null,\n" +
                "    \"pick_shift\": [2],\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"name\": \"Áo Polo\",\n" +
                "            \"code\": \"Polo123\",\n" +
                "            \"quantity\": 5,\n" +
                "            \"price\": 200000,\n" +
                "            \"length\": 12,\n" +
                "            \"width\": 12,\n" +
                "            \"height\": 12,\n" +
                "            \"weight\": 3200,\n" +
                "            \"category\": {\n" +
                "                \"level1\": \"Áo\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("ShopId", shopId);
            connection.setRequestProperty("Token", token);
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response

            // Parse the JSON response if needed
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject data = jsonResponse.getAsJsonObject("data");
            String totalFee = data.get("total_fee").getAsString();
            String deliveryDate = data.get("expected_delivery_time").getAsString();
            dataRespone.setDeliveryFee(totalFee);
            dataRespone.setDeliveryDate(deliveryDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataRespone;
    }

    public static void main(String[] args) {
        System.out.println(ApiGHN.getData("Thu duc hcm city", "20308", "1444").toString());
    }
}
