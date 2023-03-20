package lk.ijse.thogakade.dto.tm;

/*
    @author DanujaV
    @created 3/20/23 - 10:06 AM   
*/

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTM {
    String id;
    String name;
    String address;
    Double salary;
}
