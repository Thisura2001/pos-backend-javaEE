package lk.ijse.posbackendjavaee.Bo.impl;

import jakarta.transaction.Transactional;
import lk.ijse.posbackendjavaee.Bo.orderBo;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDaoImpl;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDetailDaoImpl;
import lk.ijse.posbackendjavaee.Dto.OrderDetailDto;
import lk.ijse.posbackendjavaee.Dto.OrderDto;
import lk.ijse.posbackendjavaee.Dto.OrderRequest;
import lk.ijse.posbackendjavaee.Entity.OrderDetailEntity;
import lk.ijse.posbackendjavaee.Entity.OrderEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderBoImpl implements orderBo {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    OrderDetailDaoImpl orderDetailDao = new OrderDetailDaoImpl();

    @Override
    public boolean SaveOrder(OrderDto orderDto, Connection connection) {
        return false;
    }

    @Transactional
    @Override
    public boolean saveOrderWithDetails(OrderRequest orderRequest, Connection connection) {
        try {
            // Save the order first
            boolean orderSaved = orderDao.saveOrder(new OrderEntity(
                    orderRequest.getOrderData().getOrderId(),
                    orderRequest.getOrderData().getAmount(),
                    orderRequest.getOrderData().getNetTotal(),
                    orderRequest.getOrderData().getDiscount(),
                    orderRequest.getOrderData().getFinalTotal()), connection);

            // If the order is saved, save the order details
            if (orderSaved) {
                return new OrderDetailDaoImpl().saveOrderDetail(new OrderDetailEntity(
                                orderRequest.getOrderDetails().getOrderId(),
                                orderRequest.getOrderDetails().getItemCode(),
                                orderRequest.getOrderDetails().getQty(),
                                orderRequest.getOrderDetails().getUnitPrice(),
                                orderRequest.getOrderDetails().getCustomerId(),
                                orderRequest.getOrderDetails().getItemName()), connection);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
