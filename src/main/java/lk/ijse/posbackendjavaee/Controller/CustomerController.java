package lk.ijse.posbackendjavaee.Controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackendjavaee.Bo.impl.CustomerBoImpl;
import lk.ijse.posbackendjavaee.Dao.CustomerDao;
import lk.ijse.posbackendjavaee.Dao.Impl.CustomerDaoImpl;
import lk.ijse.posbackendjavaee.Dto.CustomerDto;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {
    CustomerBoImpl customerBo = new CustomerBoImpl();
    Connection connection;
    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/stuRegistration");
            this.connection =  pool.getConnection();
        }catch (NamingException | SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerBoImpl customerBo = new CustomerBoImpl();
        List<CustomerDto> allCustomers = customerBo.getAllCustomers(connection);
        try (var writer = resp.getWriter()){
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(allCustomers,writer);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json")||req.getContentType()==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);
            System.out.println(customerDto);
            boolean saveCustomer = customerBo.saveCustomer(customerDto,connection);
            if (saveCustomer){
                writer.write("Customer Saved!!");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("Failed!!");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);
            boolean updateCustomer = customerBo.update(customerDto,connection);
            if (updateCustomer){
                writer.write("Customer Update Success!!");
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            }else {
                writer.write("Try again!!");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try (var writer = resp.getWriter()){
            boolean deleteCustomer = customerBo.delete(id,connection);
            if (deleteCustomer){
                writer.write("Delete Success");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("Try again");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
