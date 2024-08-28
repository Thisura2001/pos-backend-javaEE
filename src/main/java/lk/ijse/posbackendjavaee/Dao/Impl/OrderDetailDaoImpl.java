package lk.ijse.posbackendjavaee.Dao.Impl;

import lk.ijse.posbackendjavaee.Dao.OrderDetailDao;
import lk.ijse.posbackendjavaee.Entity.OrderDetailEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static lk.ijse.posbackendjavaee.Controller.OrderController.logger;

public class OrderDetailDaoImpl implements OrderDetailDao {

    static String SAVE_ORDER_DETAIL = "INSERT INTO orderdetail (orderId,itemCode,qty,unitPrice,customerId,itemName) VALUES (?,?,?,?,?,?)";

    public boolean saveOrderDetail(OrderDetailEntity orderDetailDto, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ORDER_DETAIL);
            preparedStatement.setString(1, orderDetailDto.getOrderId());
            preparedStatement.setString(2, orderDetailDto.getItemCode());
            preparedStatement.setString(3, orderDetailDto.getQty());
            preparedStatement.setString(4, orderDetailDto.getUnitPrice());
            preparedStatement.setString(5, orderDetailDto.getCustomerId());
            preparedStatement.setString(6, orderDetailDto.getItemName());


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Order detail saved successfully.");
                return true;
            } else {
                logger.error("Failed to save order detail; no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
