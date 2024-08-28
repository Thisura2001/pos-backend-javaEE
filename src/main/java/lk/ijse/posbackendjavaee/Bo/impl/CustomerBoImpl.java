package lk.ijse.posbackendjavaee.Bo.impl;

import lk.ijse.posbackendjavaee.Bo.Custom.CustomerBo;
import lk.ijse.posbackendjavaee.Dao.Impl.CustomerDaoImpl;
import lk.ijse.posbackendjavaee.Dto.CustomerDto;
import lk.ijse.posbackendjavaee.Entity.CustomerEntity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
   CustomerDaoImpl customerDao =  new CustomerDaoImpl();
    @Override
    public List<CustomerDto> getAllCustomers(Connection connection) {
        List<CustomerDto> customerDto = new ArrayList<>();
        List<CustomerEntity>customerEntities = customerDao.getAllCustomers(connection);
        for (CustomerEntity customer:customerEntities) {
            customerDto.add(new CustomerDto(customer.getId(),customer.getName(),customer.getSalary(),customer.getAddress()));
        }
        return customerDto;
    }

    @Override
    public boolean saveCustomer(CustomerDto customerDto, Connection connection) {
        return customerDao.saveCustomer(new CustomerEntity(customerDto.getId(),customerDto.getName(),customerDto.getSalary(),customerDto.getAddress()),connection);
    }

    @Override
    public boolean update(CustomerDto customerDto, Connection connection) {
        return customerDao.update(new CustomerEntity(customerDto.getId(),customerDto.getName(),customerDto.getSalary(),customerDto.getAddress()),connection);
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return customerDao.delete(id,connection);
    }
}
