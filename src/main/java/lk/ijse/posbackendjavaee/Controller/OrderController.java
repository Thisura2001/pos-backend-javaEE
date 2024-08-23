package lk.ijse.posbackendjavaee.Controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackendjavaee.Bo.impl.OrderBoImpl;
import lk.ijse.posbackendjavaee.Dto.OrderDto;
import lk.ijse.posbackendjavaee.Util.OrderIdGenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection connection;

    private final OrderIdGenerator orderIdGenerator = new OrderIdGenerator();
   OrderBoImpl orderBo = new OrderBoImpl();
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
        if (!req.getContentType().startsWith("application/json")||req.getContentType()==null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            OrderDto orderDto = jsonb.fromJson(req.getReader(), OrderDto.class);
            System.out.println(orderDto);
            boolean saveOrder = orderBo.SaveOrder(orderDto,connection);
            if (saveOrder){
                writer.write("Order saved");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("Error saving Order");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
