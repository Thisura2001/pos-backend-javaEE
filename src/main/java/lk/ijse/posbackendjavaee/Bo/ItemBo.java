package lk.ijse.posbackendjavaee.Bo;

import lk.ijse.posbackendjavaee.Dto.ItemDto;

import java.sql.Connection;
import java.util.List;

public interface ItemBo {
    List<ItemDto> getAllItems(Connection connection);

    boolean SaveItem(ItemDto itemDto, Connection connection);

    boolean Update(ItemDto itemDto, Connection connection);

    boolean deleteItem(String code, Connection connection);
}
