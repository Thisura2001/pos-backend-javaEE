package lk.ijse.posbackendjavaee.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {
    private String orderId;
    private String amount;
    private String netTotal;
    private String discount;
    private String finalTotal;
}
