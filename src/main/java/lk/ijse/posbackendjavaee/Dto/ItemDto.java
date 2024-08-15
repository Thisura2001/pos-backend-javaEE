package lk.ijse.posbackendjavaee.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto {
    private String code;
    private String itemName;
    private String price;
    private String qty;
}
