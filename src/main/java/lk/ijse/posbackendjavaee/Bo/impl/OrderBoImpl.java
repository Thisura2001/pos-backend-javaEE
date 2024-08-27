package lk.ijse.posbackendjavaee.Bo.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posbackendjavaee.Bo.orderBo;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDaoImpl;
import lk.ijse.posbackendjavaee.Dto.OrderDetailDto;
import lk.ijse.posbackendjavaee.Dto.OrderDto;
import lk.ijse.posbackendjavaee.Entity.OrderEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderBoImpl implements orderBo {
    OrderDaoImpl orderDao = new OrderDaoImpl();

    @Transactional
    @Override
    public boolean SaveOrder(OrderDto orderDto, Connection connection) {
     boolean orderSave =  orderDao.saveOrder(new OrderEntity(orderDto.getOrderId(),orderDto.getAmount(),orderDto.getNetTotal(),orderDto.getDiscount(),orderDto.getFinalTotal()),connection);
     return orderSave;
    }
}