package com.example.RestApiCoffee.entities.ingredients.supplement;

import lombok.Data;

@Data
public class SupplementDto {
    private Long id;
    private String name;
    private Double price;
    private boolean active;

    public final Supplement build(){
        Supplement supplement = new Supplement();
        supplement.setId(this.id);
        supplement.setName(this.name);
        supplement.setPrice(this.price);
        supplement.setActive(this.active);
        return supplement;
    }
}
