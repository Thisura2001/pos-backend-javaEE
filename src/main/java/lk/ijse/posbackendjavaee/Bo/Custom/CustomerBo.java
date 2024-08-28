package lk.ijse.posbackendjavaee.Bo.Custom;

import lk.ijse.posbackendjavaee.Dto.CustomerDto;

import java.sql.Connection;
import java.util.List;

public interface CustomerBo {
    List<CustomerDto> getAllCustomers(Connection connection);

    boolean saveCustomer(CustomerDto customerDto, Connection connection);

    boolean update(CustomerDto customerDto, Connection connection);

    boolean delete(String id, Connection connection);
}
