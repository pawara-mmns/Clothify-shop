package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {
    private String cus_id;
    private String cus_name;
    private String phonenumber;
    private String address;

}
