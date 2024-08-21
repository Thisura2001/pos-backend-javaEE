package lk.ijse.posbackendjavaee.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemEntity {
    private String code;
    private String itemName;
    private String price;
    private String qty;
}
