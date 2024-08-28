package lk.ijse.posbackendjavaee.Bo;

import lk.ijse.posbackendjavaee.Dto.OrderDto;
import lk.ijse.posbackendjavaee.Dto.OrderRequest;

import java.sql.Connection;

public interface orderBo {
    boolean SaveOrder(OrderDto orderDto, Connection connection);
    public boolean saveOrderWithDetails(OrderRequest orderRequest, Connection connection);
}
