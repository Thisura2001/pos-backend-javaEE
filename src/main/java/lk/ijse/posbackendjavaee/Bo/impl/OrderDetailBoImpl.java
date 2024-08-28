package lk.ijse.posbackendjavaee.Bo.impl;

import lk.ijse.posbackendjavaee.Bo.Custom.OrderDetailBo;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDetailDaoImpl;
import lk.ijse.posbackendjavaee.Dto.OrderDetailDto;
import lk.ijse.posbackendjavaee.Entity.OrderDetailEntity;

import java.sql.Connection;

public class OrderDetailBoImpl implements OrderDetailBo {
    OrderDetailDaoImpl orderDetailDao = new OrderDetailDaoImpl();
    @Override
    public boolean saveOrderDetail(OrderDetailDto orderDetailDto, Connection connection) {
        return orderDetailDao.saveOrderDetail(new OrderDetailEntity( orderDetailDto.getOrderId(),orderDetailDto.getItemCode(),orderDetailDto.getQty(),orderDetailDto.getUnitPrice(),orderDetailDto.getCustomerId(),orderDetailDto.getItemName()),connection);
    }
}
