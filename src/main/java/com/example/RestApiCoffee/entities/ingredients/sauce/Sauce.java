package com.example.RestApiCoffee.entities.ingredients.sauce;

import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
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
@Table(name = "sauces")
public class Sauce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(max = 255, message = "Name should be less than 255 characters")
    private String name;
    private Double price;
    private boolean active;

    @JoinColumn(name = "sauce_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<SandwichOrder> sandwichOrders;

    @JoinColumn(name = "sauce_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<SnackOrder> snackOrders;

    @Override
    public String toString() {
        return "Sauce{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", active=" + active +
                '}';
    }
}
