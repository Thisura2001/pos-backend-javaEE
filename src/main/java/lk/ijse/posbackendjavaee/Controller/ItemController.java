package lk.ijse.posbackendjavaee.Controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackendjavaee.Dao.Impl.ItemDaoImpl;
import lk.ijse.posbackendjavaee.Dto.ItemDto;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    Connection connection;
    @Override
    public void init() throws ServletException {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource lookup = (DataSource) initialContext.lookup("java:comp/env/jdbc/stuRegistration");
            this.connection = lookup.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ItemDto>getAllItems(){
        ItemDaoImpl itemDao = new ItemDaoImpl();
        return itemDao.getAllItems(connection);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       List<ItemDto> allItems = getAllItems();
       try (var writer = resp.getWriter()){
           resp.setContentType("application/json");
           Jsonb jsonb = JsonbBuilder.create();
           jsonb.toJson(allItems,writer);
       }catch (Exception e){
           e.printStackTrace();
           resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().startsWith("application/json")||req.getContentType()==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(req.getReader(), ItemDto.class);
            System.out.println(itemDto);
            ItemDaoImpl itemDaoImpl = new ItemDaoImpl();
            boolean saveItem = itemDaoImpl.SaveItem(itemDto,connection);
            if (saveItem){
                writer.write("item Saved Success");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("Try again");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDaoImpl itemDaoImpl = new ItemDaoImpl();
            ItemDto itemDto = jsonb.fromJson(req.getReader(), ItemDto.class);
            boolean UpdateItem = itemDaoImpl.Update(itemDto,connection);

            if (UpdateItem){
                writer.write("Update SuccessFull!!");
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            }else {
                writer.write("Try again!");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        try (var writer = resp.getWriter()){
            ItemDaoImpl itemDaoImpl = new ItemDaoImpl();
            boolean deleteItem = itemDaoImpl.deleteItem(code,connection);
            if (deleteItem){
                writer.write("Delete success");
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            }else {
                writer.write("try again!");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
