package lk.ijse.posbackendjavaee.Dao;

import lk.ijse.posbackendjavaee.Dto.CustomerDto;
import lk.ijse.posbackendjavaee.Entity.CustomerEntity;

import java.sql.Connection;
import java.util.List;

public interface CustomerDao {
    boolean saveCustomer(CustomerEntity customerEntity, Connection connection);

    CustomerDto getCustomer(String customerId, Connection connection);


    boolean delete(String id, Connection connection);

    boolean update(CustomerEntity customerEntity, Connection connection);

    List<CustomerEntity> getAllCustomers(Connection connection);
}
