package lk.ijse.posbackendjavaee.Dao;


import lk.ijse.posbackendjavaee.Dao.Impl.ItemDaoImpl;
import lk.ijse.posbackendjavaee.Dto.ItemDto;

import java.sql.Connection;

public interface ItemDao{
    boolean SaveItem(ItemDto itemDto, Connection connection);

    ItemDto getAll(String id, Connection connection);

    boolean Update(ItemDto itemDto, Connection connection);

    boolean deleteItem(String code, Connection connection);
}
