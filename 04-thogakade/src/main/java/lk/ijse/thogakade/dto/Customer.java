package lk.ijse.thogakade.dto;

/*
    @author DanujaV
    @created 3/20/23 - 9:39 AM   
*/

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Customer {
    private String id;
    private String name;
    private String address;
    private Double salary;

}
