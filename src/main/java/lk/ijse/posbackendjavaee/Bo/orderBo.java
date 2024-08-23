package lk.ijse.posbackendjavaee.Bo;

import lk.ijse.posbackendjavaee.Dto.OrderDto;

import java.sql.Connection;

public interface orderBo {
    boolean SaveOrder(OrderDto orderDto, Connection connection);
}
