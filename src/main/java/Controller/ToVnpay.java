package Controller;

import Model.VnpayService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ToVNPay", value = "/to-vnpay")
public class ToVnpay extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //get value
        int amount = req.getParameter("amount") != null ? Integer.parseInt(req.getParameter("amount")) : 0;
        String orderInfo = req.getParameter("orderInfo") != null ? req.getParameter("orderInfo") : "";
        //handle
        VnpayService vnpayService = new VnpayService();
        String url="";
        try {
            url= vnpayService.createPaymentUrl(orderInfo,"billpayment",amount,null,"vn",
                    req.getRemoteAddr(),null,null);
        } catch (Exception e) {
            System.out.println("Lỗi tạo url vnpay");
            throw new RuntimeException(e);
        }
        //redirect
        try {
            resp.sendRedirect(url);
        } catch (IOException e) {
            System.out.println("Lỗi redirect vnpay");
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //get value
        int amount = req.getParameter("amount") != null ? Integer.parseInt(req.getParameter("amount")) : 0;
        String orderInfo = req.getParameter("orderInfo") != null ? req.getParameter("orderInfo") : "";
        //handle
        VnpayService vnpayService = new VnpayService();
        String url="";
        try {
            url= vnpayService.createPaymentUrl(orderInfo,"billpayment",amount,null,"vn",
                    req.getRemoteAddr(),null,null);
        } catch (Exception e) {
            System.out.println("Lỗi tạo url vnpay");
            throw new RuntimeException(e);
        }
        //redirect
        try {
            resp.sendRedirect(url);
        } catch (IOException e) {
            System.out.println("Lỗi redirect vnpay");
            throw new RuntimeException(e);
        }
    }
}