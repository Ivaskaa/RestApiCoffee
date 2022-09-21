package com.example.RestApiCoffee.entities.order.dessert;

import com.example.RestApiCoffee.entities.ingredients.supplement.SupplementDto;
import com.example.RestApiCoffee.entities.ingredients.syrup.SyrupDto;
import com.example.RestApiCoffee.entities.product.dessert.DessertDto;
import com.example.RestApiCoffee.entities.product.dessert.DessertSize;
import lombok.Data;

@Data
public class DessertOrderDto {
    private Long id;
    private DessertDto dessert;
    private SyrupDto syrup;
    private SupplementDto supplement;
    private DessertSize size;
    private Integer count;
    private boolean active;

    public final DessertOrder build(){
        DessertOrder dessertOrder = new DessertOrder();
        dessertOrder.setId(this.id);
        if(this.dessert != null){
            dessertOrder.setDessert(this.dessert.build());
        }
        dessertOrder.setSize(this.size);
        if(this.syrup != null) {
            dessertOrder.setSyrup(this.syrup.build());
        }
        if(this.supplement != null) {
            dessertOrder.setSupplement(this.supplement.build());
        }
        dessertOrder.setCount(this.count);
        dessertOrder.setActive(this.active);
        return dessertOrder;
    }
}
