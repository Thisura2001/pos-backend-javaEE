package lk.ijse.posbackendjavaee.Entity;

import lk.ijse.posbackendjavaee.Dto.OrderDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
