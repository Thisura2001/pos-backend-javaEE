package lk.ijse.posbackendjavaee.Dao.Custom;


import lk.ijse.posbackendjavaee.Dto.ItemDto;
import lk.ijse.posbackendjavaee.Entity.ItemEntity;

import java.sql.Connection;
import java.util.List;

public interface ItemDao{
    boolean SaveItem(ItemEntity itemEntity, Connection connection);

    ItemDto getAll(String id, Connection connection);

    boolean Update(ItemEntity itemEntity, Connection connection);

    boolean deleteItem(String code, Connection connection);

    List<ItemEntity> getAllItems(Connection connection);
}
