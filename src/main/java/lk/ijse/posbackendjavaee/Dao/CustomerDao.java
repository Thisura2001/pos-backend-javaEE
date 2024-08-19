package lk.ijse.posbackendjavaee.Dao;

import lk.ijse.posbackendjavaee.Dto.CustomerDto;

import java.sql.Connection;
import java.util.List;

public interface CustomerDao {
    boolean saveCustomer(CustomerDto customerDto, Connection connection);

    CustomerDto getCustomer(String customerId, Connection connection);


    boolean delete(String id, Connection connection);

    boolean update(CustomerDto customerDto, Connection connection);

    List<CustomerDto> getAllCustomers(Connection connection);
}
