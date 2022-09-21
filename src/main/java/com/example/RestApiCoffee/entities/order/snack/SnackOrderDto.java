package com.example.RestApiCoffee.entities.order.snack;

import com.example.RestApiCoffee.entities.ingredients.sauce.SauceDto;
import com.example.RestApiCoffee.entities.ingredients.supplement.SupplementDto;
import com.example.RestApiCoffee.entities.product.snack.SnackDto;
import com.example.RestApiCoffee.entities.product.snack.SnackSize;
import lombok.Data;

@Data
public class SnackOrderDto {
    private Long id;
    private SnackDto snack;
    private SauceDto sauce;
    private SupplementDto supplement;
    private SnackSize size;
    private Integer count;
    private boolean active;

    public final SnackOrder build(){
        SnackOrder snackOrder = new SnackOrder();
        snackOrder.setId(this.id);
        if(this.snack != null) {
            snackOrder.setSnack(this.snack.build());
        }
        snackOrder.setSize(this.size);
        if(this.sauce != null) {
            snackOrder.setSauce(this.sauce.build());
        }
        if(this.supplement != null) {
            snackOrder.setSupplement(this.supplement.build());
        }
        snackOrder.setCount(this.count);
        snackOrder.setActive(this.active);
        return snackOrder;
    }
}
