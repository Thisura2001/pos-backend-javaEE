package lk.ijse.posbackendjavaee.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackendjavaee.Util.OrderIdGenerator;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final OrderIdGenerator orderIdGenerator = new OrderIdGenerator();
    @Override
    public void init() throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Assuming 'orderIdGenerator' is an instance of a class like the one we discussed earlier
        String orderId = orderIdGenerator.generateOrderId();

        // Set the response content type to JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Write the JSON response
        PrintWriter out = resp.getWriter();
        out.print("{\"orderId\": \"" + orderId + "\"}");
        out.flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
