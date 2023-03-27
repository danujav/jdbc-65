package lk.ijse.thogakade.dto;

/*
    @author DanujaV
    @created 3/27/23 - 10:27 AM   
*/

import lombok.*;

@Data
@AllArgsConstructor
public class CartDTO {
    String code;
    Integer qty;
    Double unitPrice;
}
