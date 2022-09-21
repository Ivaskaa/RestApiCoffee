package com.example.RestApiCoffee.entities.city;

import lombok.Data;

@Data
public class CityDto {
    private Long id;
    private String name;
    private boolean active;

    public final City build(){
        City city = new City();
        city.setId(this.id);
        city.setName(this.name);
        city.setActive(this.active);
        return city;
    }
}
