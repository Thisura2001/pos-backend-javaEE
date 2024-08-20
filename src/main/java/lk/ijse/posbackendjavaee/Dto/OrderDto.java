package lk.ijse.posbackendjavaee.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private String orderId;
    private String orderItems;
    private String netTotal;
    private String discount;
    private String finalTotal;
}
