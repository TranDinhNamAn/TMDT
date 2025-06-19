package Controller;

import Model.Cart;
import com.paypal.orders.*;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PaypalController", value = "/paypal")
public class PaypalController extends HttpServlet {

    // Thay bằng ClientID và Secret Key của bạn
    private static final String CLIENT_ID = "AaN578lZrRGNNve6iQ36cjdDgh8WyH4sGpmaoCHavSgit0cZwu8vkD0ltmXycGMQL7nGnejXE-Z2jolg";
    private static final String CLIENT_SECRET = "ECUO-t8J9ql0FRggc-N93Z_kEURxcHFOwK8MeVFZZDiJPXiBTLoGnx6RBx9JDXGRpT7hHWz_R2K6ZXpH";

    private PayPalHttpClient getClient() {
        PayPalEnvironment environment = new PayPalEnvironment.Sandbox(CLIENT_ID, CLIENT_SECRET);
        return new PayPalHttpClient(environment);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PayPalHttpClient client = getClient();

        List<Cart> cartItems = (List<Cart>) request.getSession().getAttribute("cartItems");
        if (cartItems == null || cartItems.isEmpty()) {
            response.sendRedirect("payment");
            return;
        }

        double totalAmount = 0;
        for (Cart item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl("http://localhost:8080/TTWeb/paypal-success")
                .cancelUrl("http://localhost:8080/TTWeb/payment");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();
        purchaseUnits.add(new PurchaseUnitRequest()
                .amountWithBreakdown(new AmountWithBreakdown()
                        .currencyCode("USD")
                        .value(String.format("%.2f", totalAmount))));

        orderRequest.purchaseUnits(purchaseUnits);

        OrdersCreateRequest requestOrder = new OrdersCreateRequest();
        requestOrder.requestBody(orderRequest);

        try {
            com.paypal.http.HttpResponse<Order> responseOrder = client.execute(requestOrder);
            for (LinkDescription link : responseOrder.result().links()) {
                if (link.rel().equals("approve")) {
                    response.sendRedirect(link.href()); // Chuyển hướng người dùng đến PayPal
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi kết nối PayPal: " + e.getMessage());
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }
    }
}