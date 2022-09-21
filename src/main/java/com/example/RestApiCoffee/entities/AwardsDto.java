package com.example.RestApiCoffee.entities;

import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import lombok.Data;

import java.util.Set;

@Data
public class AwardsDto {
    private Set<CoffeeOrder> coffeeOrder;
    private Set<TeaOrder> teaOrder;
    private Set<DessertOrder> dessertOrder;
    private Set<SandwichOrder> sandwichOrder;
    private Set<SnackOrder> snackOrder;
}
