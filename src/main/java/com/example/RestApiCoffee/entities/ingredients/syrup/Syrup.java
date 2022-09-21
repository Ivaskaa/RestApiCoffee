package com.example.RestApiCoffee.entities.ingredients.syrup;

import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "syrups")
public class Syrup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(max = 255, message = "Name should be less than 255 characters")
    private String name;
    private Double price;
    private boolean active;

    @JoinColumn(name = "syrup_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<CoffeeOrder> coffeeOrders;

    @JoinColumn(name = "syrup_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<TeaOrder> teaOrders;

    @JoinColumn(name = "syrup_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<DessertOrder> dessertOrders;

    @Override
    public String toString() {
        return "Syrup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", active=" + active +
                '}';
    }
}
