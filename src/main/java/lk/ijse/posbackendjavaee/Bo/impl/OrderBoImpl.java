package lk.ijse.posbackendjavaee.Bo.impl;

import lk.ijse.posbackendjavaee.Bo.orderBo;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDaoImpl;
import lk.ijse.posbackendjavaee.Dto.OrderDto;
import lk.ijse.posbackendjavaee.Entity.OrderEntity;

import java.sql.Connection;

public class OrderBoImpl implements orderBo {
    OrderDaoImpl orderDao = new OrderDaoImpl();

    @Override
    public boolean SaveOrder(OrderDto orderDto, Connection connection) {
        return orderDao.saveOrder(new OrderEntity(orderDto.getOrderId(),orderDto.getOrderItems(),orderDto.getNetTotal(),orderDto.getDiscount(),orderDto.getFinalTotal()),connection);
    }
}
