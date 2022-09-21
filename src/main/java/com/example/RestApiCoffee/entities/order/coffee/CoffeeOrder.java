package com.example.RestApiCoffee.entities.order.coffee;

import com.example.RestApiCoffee.entities.AwardsDto;
import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import com.example.RestApiCoffee.entities.product.coffee.CoffeeSize;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "coffee_orders")
public class CoffeeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "coffee_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Coffee coffee;

    @JoinColumn(name = "syrup_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Syrup syrup;

    @JoinColumn(name = "alcohol_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Alcohol alcohol;

    @JoinColumn(name = "milk_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Milk milk;

    @JoinColumn(name = "supplement_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Supplement supplement;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private CoffeeSize size;

    private Integer count;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonBackReference
    private Order order;

    private boolean active;

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "id=" + id +
                ", coffee=" + coffee +
                ", syrup=" + syrup +
                ", alcohol=" + alcohol +
                ", milk=" + milk +
                ", supplement=" + supplement +
                ", size=" + size +
                ", count=" + count +
                '}';
    }
}
