package lk.ijse.posbackendjavaee.Bo.impl;

import lk.ijse.posbackendjavaee.Bo.Custom.ItemBo;
import lk.ijse.posbackendjavaee.Dao.Impl.ItemDaoImpl;
import lk.ijse.posbackendjavaee.Dto.ItemDto;
import lk.ijse.posbackendjavaee.Entity.ItemEntity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDaoImpl itemDao = new ItemDaoImpl();
    @Override
    public List<ItemDto> getAllItems(Connection connection) {
        List<ItemDto>itemDtoList = new ArrayList<>();
        List<ItemEntity>itemEntities = itemDao.getAllItems(connection);
        for (ItemEntity item:itemEntities) {
            itemDtoList.add(new ItemDto(item.getCode(),item.getItemName(),item.getPrice(),item.getQty()));
        }
        return itemDtoList;
    }

    @Override
    public boolean SaveItem(ItemDto itemDto, Connection connection) {
        return itemDao.SaveItem(new ItemEntity(itemDto.getCode(),itemDto.getItemName(),itemDto.getPrice(),itemDto.getQty()),connection);
    }

    @Override
    public boolean Update(ItemDto itemDto, Connection connection) {
        return itemDao.Update(new ItemEntity(itemDto.getCode(),itemDto.getItemName(),itemDto.getPrice(),itemDto.getQty()),connection);
    }

    @Override
    public boolean deleteItem(String code, Connection connection) {
        return itemDao.deleteItem(code,connection);
    }

    @Override
    public ItemDto getAll(String id, Connection connection) {
        return itemDao.getAll(id,connection);
    }

}
