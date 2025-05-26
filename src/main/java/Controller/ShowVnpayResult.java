package Controller;

import Model.VnpayService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
@WebServlet(name = "ShowVnpayPaymentResults", value = "/show-vnpay-payment-results")
public class ShowVnpayResult extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> paramMap = req.getParameterMap();

        String[] vnpPayDate = paramMap.get("vnp_PayDate");
        String formattedVnpPayDate = VnpayService.formatVNPayDate(vnpPayDate[0]);

        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            req.setAttribute(key, value);
        }

        req.setAttribute("vnp_PayDate", formattedVnpPayDate);
        req.getRequestDispatcher("/return-vnpay.jsp").forward(req, resp);
    }
}
