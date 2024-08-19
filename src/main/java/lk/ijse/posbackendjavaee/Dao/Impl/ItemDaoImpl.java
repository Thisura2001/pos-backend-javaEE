package lk.ijse.posbackendjavaee.Dao.Impl;

import lk.ijse.posbackendjavaee.Dao.ItemDao;
import lk.ijse.posbackendjavaee.Dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class ItemDaoImpl implements ItemDao {
    static String SAVE_ITEM = "INSERT INTO item (code,itemName,price,qty) VALUES (?,?,?,?)";
    static String GET_ITEM = "SELECT * FROM item WHERE code=?";
    static String GET_ALL = "SELECT * FROM item";
    static String DELETE_ITEM = "DELETE FROM item WHERE code=?";
    static String UPDATE_ITEM = "UPDATE item SET itemName=?,price=?,qty=? WHERE code=?";

    @Override
    public boolean SaveItem(ItemDto itemDto, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ITEM);
            preparedStatement.setString(1,itemDto.getCode());
            preparedStatement.setString(2,itemDto.getItemName());
            preparedStatement.setString(3,itemDto.getPrice());
            preparedStatement.setString(4,itemDto.getQty());

            return preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ItemDto getAll(String id, Connection connection) {
        ItemDto itemDto = new ItemDto();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                itemDto.setCode(resultSet.getString("code"));
                itemDto.setItemName(resultSet.getString("itemName"));
                itemDto.setPrice(resultSet.getString("price"));
                itemDto.setQty(resultSet.getString("qty"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemDto;
    }

    @Override
    public boolean Update(ItemDto itemDto, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM);
            preparedStatement.setString(1,itemDto.getItemName());
            preparedStatement.setString(2,itemDto.getPrice());
            preparedStatement.setString(3,itemDto.getQty());
            preparedStatement.setString(4,itemDto.getCode());
            return preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteItem(String code, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM);
            preparedStatement.setString(1,code);
            return preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<ItemDto> getAllItems(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ItemDto>itemDtoList = new ArrayList<>();
            while (resultSet.next()){
                ItemDto itemDto = new ItemDto();
                itemDto.setCode(resultSet.getString("code"));
                itemDto.setItemName(resultSet.getString("itemName"));
                itemDto.setPrice(resultSet.getString("price"));
                itemDto.setQty(resultSet.getString("qty"));
                itemDtoList.add(itemDto);
            }
            return itemDtoList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
