package lk.ijse.thogakade.dto;

/*
    @author DanujaV
    @created 3/13/23 - 2:01 PM   
*/

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private String code;
    private String description;
//    double unitPrice;   //premitive data type
    private Double unitPrice;
//    int qtyOnHand;
    private Integer qtyOnHand;
}
