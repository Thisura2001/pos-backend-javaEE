package lk.ijse.posbackendjavaee.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private OrderDto orderData;
    private OrderDetailDto orderDetails;
}
