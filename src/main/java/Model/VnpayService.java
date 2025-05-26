package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VnpayService {
    public  String createPaymentUrl(String orderInfo, String orderType, long amount, String bankCode,
                                          String language, String ipAddress, Map<String, String> billingInfo,
                                          Map<String, String> invoiceInfo) {
        Map<String, String> vnp_Params = new HashMap<>();

        // Thông tin bắt buộc
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", Config.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", Config.getRandomNumber(8));
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", (language != null && !language.isEmpty()) ? language : "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", ipAddress);

        // Thời gian tạo & hết hạn
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnp_Params.put("vnp_CreateDate", formatter.format(cld.getTime()));
        cld.add(Calendar.MINUTE, 15);
        vnp_Params.put("vnp_ExpireDate", formatter.format(cld.getTime()));

        // Ngân hàng
        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }

        // Billing info
        if (billingInfo != null) {
            putIfNotEmpty(vnp_Params, "vnp_Bill_Mobile", billingInfo.get("mobile"));
            putIfNotEmpty(vnp_Params, "vnp_Bill_Email", billingInfo.get("email"));

            String fullName = billingInfo.get("fullname");
            if (fullName != null && !fullName.trim().isEmpty()) {
                String[] parts = fullName.trim().split("\\s+");
                if (parts.length >= 2) {
                    vnp_Params.put("vnp_Bill_FirstName", parts[0]);
                    vnp_Params.put("vnp_Bill_LastName", parts[parts.length - 1]);
                }
            }

            putIfNotEmpty(vnp_Params, "vnp_Bill_Address", billingInfo.get("address"));
            putIfNotEmpty(vnp_Params, "vnp_Bill_City", billingInfo.get("city"));
            putIfNotEmpty(vnp_Params, "vnp_Bill_Country", billingInfo.get("country"));
            putIfNotEmpty(vnp_Params, "vnp_Bill_State", billingInfo.get("state"));
        }

        // Invoice info
        if (invoiceInfo != null) {
            putIfNotEmpty(vnp_Params, "vnp_Inv_Phone", invoiceInfo.get("phone"));
            putIfNotEmpty(vnp_Params, "vnp_Inv_Email", invoiceInfo.get("email"));
            putIfNotEmpty(vnp_Params, "vnp_Inv_Customer", invoiceInfo.get("customer"));
            putIfNotEmpty(vnp_Params, "vnp_Inv_Address", invoiceInfo.get("address"));
            putIfNotEmpty(vnp_Params, "vnp_Inv_Company", invoiceInfo.get("company"));
            putIfNotEmpty(vnp_Params, "vnp_Inv_Taxcode", invoiceInfo.get("taxcode"));
            putIfNotEmpty(vnp_Params, "vnp_Inv_Type", invoiceInfo.get("type"));
        }

        // Build query & hash
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (int i = 0; i < fieldNames.size(); i++) {
            String name = fieldNames.get(i);
            String value = vnp_Params.get(name);
            if (value != null && !value.isEmpty()) {
                hashData.append(name).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(name, StandardCharsets.US_ASCII))
                        .append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII));
                if (i < fieldNames.size() - 1) {
                    hashData.append('&');
                    query.append('&');
                }
            }
        }

        String secureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        query.append("&vnp_SecureHash=").append(secureHash);

        return Config.vnp_PayUrl + "?" + query;
    }

    public static String callVnpQueryAPI(
            String tmnCode, String txnRef, String transactionDate, String ipAddr
    ) throws Exception {

        String requestId = String.valueOf(System.currentTimeMillis());
        String version = "2.1.0";
        String command = "querydr";
        String createDate = transactionDate;

        Map<String, String> params = new HashMap<>();
        params.put("vnp_RequestId", requestId);
        params.put("vnp_Version", version);
        params.put("vnp_Command", command);
        params.put("vnp_TmnCode", tmnCode);
        params.put("vnp_TxnRef", txnRef);
        params.put("vnp_TransactionDate", transactionDate);
        params.put("vnp_CreateDate", createDate);
        params.put("vnp_IpAddr", ipAddr);
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        for (int i = 0; i < fieldNames.size(); i++) {
            String key = fieldNames.get(i);
            String value = params.get(key);
            hashData.append(key).append("=").append(value);
            if (i < fieldNames.size() - 1) {
                hashData.append("&");
            }
        }
        String secureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        params.put("vnp_SecureHash", secureHash);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (postData.length() != 0) postData.append("&");
            postData.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            postData.append("=");
            postData.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        URL url = new URL("https://sandbox.vnpayment.vn/merchant_webapi/merchant.html");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(postData.toString().getBytes(StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }

    private static void putIfNotEmpty(Map<String, String> map, String key, String value) {
        if (value != null && !value.trim().isEmpty()) {
            map.put(key, value.trim());
        }
    }

    public Map<String, String> getVnpParamsFromUrl(String url) {
        Map<String, String> paramsMap = new HashMap<>();

        String queryString = url.contains("?") ? url.substring(url.indexOf("?") + 1) : url;

        String[] params = queryString.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                paramsMap.put(key, value);
            }
        }

        return paramsMap;
    }

    public static String formatVNPayDate(String vnpDateStr) {
        if (vnpDateStr == null || vnpDateStr.length() != 14) {
            return "";
        }

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Date date = inputFormat.parse(vnpDateStr);
            return outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) throws Exception {
        VnpayService service = new VnpayService();
//        String url = service.createPaymentUrl("orderInfo","billpayment",10000,null,"vn",
//                "127.0.0.1",null,null);
//        System.out.println("Generated Payment URL: " + url);

        Map<String, String> txnRef = service.getVnpParamsFromUrl("https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?vnp_Amount=1000000&vnp_Command=pay&vnp_CreateDate=20250506234759&vnp_CurrCode=VND&vnp_ExpireDate=20250507000259&vnp_IpAddr=127.0.0.1&vnp_Locale=vn&vnp_OrderInfo=orderInfo&vnp_OrderType=billpayment&vnp_ReturnUrl=http%3A%2F%2Flocalhost%3A8080%2Fvnpay_return.jsp&vnp_TmnCode=VSFKCF0M&vnp_TxnRef=33588536&vnp_Version=2.1.0&vnp_SecureHash=dc6eafdea65c953f586ead43b6fb6efe70c8e7407e21761d7c0726b8737bd27af9ae40f08829f8e77117aebfaa42a26458485eae007278ea70f807b7d02f2a0d");
        System.out.println("Transaction Reference: " + txnRef.get("vnp_TxnRef"));
        System.out.println("Create Date: " + txnRef.get("vnp_CreateDate"));
        String response = callVnpQueryAPI(Config.vnp_TmnCode, txnRef.get("vnp_TxnRef"), txnRef.get("vnp_CreateDate"), "127.0.0.1");
        System.out.println("Response: " + response);
    }
}
