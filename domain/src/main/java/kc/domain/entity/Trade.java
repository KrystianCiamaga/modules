package kc.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trade  {


    private String state;
    private String clientName;
    private Double price;
    private Integer notional;
    private Product product;



}



