package lk.ijse.posbackendjavaee.Dao.Impl;

import lk.ijse.posbackendjavaee.Dao.CustomerDao;
import lk.ijse.posbackendjavaee.Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    static String SAVE_CUSTOMER = "INSERT INTO customer (id,name,salary,address) VALUES (?,?,?,?)";
    static String GET_CUSTOMER = "SELECT * FROM customer WHERE id=?";
    static String DELETE_CUSTOMER = "DELETE FROM customer WHERE id=?";
    static String UPDATE_CUSTOMER = "UPDATE customer SET name=?,salary=?,address=? WHERE id=?";
    static String GET_ALL = "SELECT * FROM customer";

    @Override
    public boolean saveCustomer(CustomerDto customerDto, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CUSTOMER);
            preparedStatement.setString(1, customerDto.getId());
            preparedStatement.setString(2, customerDto.getName());
            preparedStatement.setString(3, customerDto.getSalary());
            preparedStatement.setString(4, customerDto.getAddress());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public CustomerDto getCustomer(String customerId, Connection connection) {
        CustomerDto customerDto = new CustomerDto();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER);
            preparedStatement.setString(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerDto.setId(resultSet.getString("id"));
                customerDto.setName(resultSet.getString("name"));
                customerDto.setSalary(resultSet.getString("salary"));
                customerDto.setAddress(resultSet.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDto;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
            preparedStatement.setString(1,id);
            return preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
        }

    @Override
    public boolean update(CustomerDto customerDto, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
            preparedStatement.setString(1,customerDto.getName());
            preparedStatement.setString(2,customerDto.getSalary());
            preparedStatement.setString(3,customerDto.getAddress());
            preparedStatement.setString(4,customerDto.getId());

            return preparedStatement.executeUpdate()>0;

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<CustomerDto> getAllCustomers(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CustomerDto> allCustomers = new ArrayList<>();
            while (resultSet.next()){
                CustomerDto customerDto = new CustomerDto();
                customerDto.setId(resultSet.getString("id"));
                customerDto.setName(resultSet.getString("name"));
                customerDto.setSalary(resultSet.getString("salary"));
                customerDto.setAddress(resultSet.getString("address"));
                allCustomers.add(customerDto);
            }
            return allCustomers;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
