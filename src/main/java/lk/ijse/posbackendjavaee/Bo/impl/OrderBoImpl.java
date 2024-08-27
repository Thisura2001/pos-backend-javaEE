package lk.ijse.posbackendjavaee.Bo.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posbackendjavaee.Bo.orderBo;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDaoImpl;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDetailDaoImpl;
import lk.ijse.posbackendjavaee.Dto.OrderDetailDto;
import lk.ijse.posbackendjavaee.Dto.OrderDto;
import lk.ijse.posbackendjavaee.Entity.OrderEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderBoImpl implements orderBo {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    OrderDetailDaoImpl orderDetailDao = new OrderDetailDaoImpl();

    @Transactional
    @Override
    public boolean SaveOrder(OrderDto orderDto, Connection connection) {
        try {
            connection.setAutoCommit(false);
            boolean saveOrder = orderDao.saveOrder(new OrderEntity(orderDto.getOrderId(),orderDto.getAmount(),orderDto.getNetTotal(),orderDto.getDiscount(),orderDto.getFinalTotal()),connection);

            if (!saveOrder) {
                connection.rollback();
                return false;
            }
            for (OrderDetailDto orderDetailDto : orderDto.getOrderDetails()) {
                orderDetailDto.setOrderId(orderDto.getOrderId());
                boolean saveOrderDetail = orderDetailDao.saveOrderDetail(orderDetailDto,connection);
                if (!saveOrderDetail) {
                    connection.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

     for (OrderDetailDto orderDetailDto : orderDto.getOrderDetails()) {
         orderDetailDao.saveOrderDetail(orderDetailDto,connection);
     }
        return true;
    }
}