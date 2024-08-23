package lk.ijse.posbackendjavaee.Dao;

import lk.ijse.posbackendjavaee.Entity.OrderEntity;

import java.sql.Connection;

public interface OrderDao {
    boolean saveOrder(OrderEntity orderEntity, Connection connection);
}
