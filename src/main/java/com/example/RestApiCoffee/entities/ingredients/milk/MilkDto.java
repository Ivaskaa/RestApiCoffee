package com.example.RestApiCoffee.entities.ingredients.milk;

import lombok.Data;

@Data
public class MilkDto {
    private Long id;
    private String name;
    private Double price;
    private boolean active;
    public final Milk build(){
        Milk milk = new Milk();
        milk.setId(this.id);
        milk.setName(this.name);
        milk.setPrice(this.price);
        milk.setActive(this.active);
        return milk;
    }
}
