
package lk.ijse.posbackendjavaee.Dao.Impl;

import lk.ijse.posbackendjavaee.Dao.OrderDao;
import lk.ijse.posbackendjavaee.Dto.OrderDetailDto;
import lk.ijse.posbackendjavaee.Entity.OrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {
    static String SAVE_ORDER = "INSERT INTO orders (orderId,amount,netTotal,discount,finalTotal) VALUES (?,?,?,?,?)";

    @Override
    public boolean saveOrder(OrderEntity orderEntity, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ORDER);
            preparedStatement.setString(1, orderEntity.getOrderId());
            preparedStatement.setString(2, orderEntity.getAmount());
            preparedStatement.setString(3, orderEntity.getNetTotal());
            preparedStatement.setString(4, orderEntity.getDiscount());
            preparedStatement.setString(5, orderEntity.getFinalTotal());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
