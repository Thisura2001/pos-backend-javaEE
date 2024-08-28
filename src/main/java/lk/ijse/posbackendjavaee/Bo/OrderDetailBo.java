package lk.ijse.posbackendjavaee.Bo;

import lk.ijse.posbackendjavaee.Dto.OrderDetailDto;

import java.sql.Connection;

public interface OrderDetailBo {
    boolean saveOrderDetail(OrderDetailDto orderDetailDto, Connection connection);
}
