package com.example.RestApiCoffee.entities.order.tea;

import com.example.RestApiCoffee.entities.ingredients.milk.MilkDto;
import com.example.RestApiCoffee.entities.ingredients.supplement.SupplementDto;
import com.example.RestApiCoffee.entities.ingredients.syrup.SyrupDto;
import com.example.RestApiCoffee.entities.product.tea.TeaDto;
import com.example.RestApiCoffee.entities.product.tea.TeaSize;
import lombok.Data;

@Data
public class TeaOrderDto {
    private Long id;
    private TeaDto tea;
    private SyrupDto syrup;
    private MilkDto milk;
    private SupplementDto supplement;
    private TeaSize size;
    private Integer count;
    private boolean active;
    
    public final TeaOrder build(){
        TeaOrder teaOrder = new TeaOrder();
        teaOrder.setId(this.id);
        if(this.tea != null) {
            teaOrder.setTea(this.tea.build());
        }
        teaOrder.setSize(this.size);
        if(this.syrup != null) {
            teaOrder.setSyrup(this.syrup.build());
        }
        if(this.milk != null) {
            teaOrder.setMilk(this.milk.build());
        }
        if(this.supplement != null) {
            teaOrder.setSupplement(this.supplement.build());
        }
        teaOrder.setCount(this.count);
        teaOrder.setActive(this.active);
        return teaOrder;
    }
}
