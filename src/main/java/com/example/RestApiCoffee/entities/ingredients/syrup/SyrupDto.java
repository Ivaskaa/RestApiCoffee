package com.example.RestApiCoffee.entities.ingredients.syrup;

import lombok.Data;

@Data
public class SyrupDto {

    private Long id;
    private String name;
    private Double price;
    private boolean active;

    public final Syrup build(){
        Syrup syrup = new Syrup();
        syrup.setId(this.id);
        syrup.setName(this.name);
        syrup.setPrice(this.price);
        syrup.setActive(this.active);
        return syrup;
    }
}
