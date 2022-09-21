package com.example.RestApiCoffee.entities.city;

import com.example.RestApiCoffee.entities.order.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JoinColumn(name = "city_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Order> orders;
    private boolean active;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
