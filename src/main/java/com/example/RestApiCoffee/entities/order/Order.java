package com.example.RestApiCoffee.entities.order;


import com.example.RestApiCoffee.entities.AwardsDto;
import com.example.RestApiCoffee.entities.PaymentForm;
import com.example.RestApiCoffee.entities.city.City;
import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import com.example.RestApiCoffee.entities.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private User user;
    private String date;
    private String time;
    @JoinColumn(name = "order_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private Set<CoffeeOrder> coffeeOrder;
    @JoinColumn(name = "order_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private Set<TeaOrder> teaOrder;
    @JoinColumn(name = "order_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private Set<DessertOrder> dessertOrder;
    @JoinColumn(name = "order_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private Set<SandwichOrder> sandwichOrder;
    @JoinColumn(name = "order_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private Set<SnackOrder> snackOrder;
    @JoinColumn(name = "city_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private City city;
    private String home;
    private String entrance;
    private String flat;
    @JoinColumn(name = "paymentForm_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private PaymentForm paymentForm;
    private String prepareCash;
    private String comment;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", coffeeOrder=" + coffeeOrder +
                ", teaOrder=" + teaOrder +
                ", dessertOrder=" + dessertOrder +
                ", sandwichOrder=" + sandwichOrder +
                ", snackOrder=" + snackOrder +
                ", city=" + city +
                ", home='" + home + '\'' +
                ", entrance='" + entrance + '\'' +
                ", flat='" + flat + '\'' +
                ", paymentForm=" + paymentForm +
                ", prepareCash='" + prepareCash + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public AwardsDto buildAwards(){
        AwardsDto awardsDto = new AwardsDto();
        awardsDto.setCoffeeOrder(this.coffeeOrder);
        awardsDto.setDessertOrder(this.dessertOrder);
        awardsDto.setSandwichOrder(this.sandwichOrder);
        awardsDto.setSnackOrder(this.snackOrder);
        awardsDto.setTeaOrder(this.teaOrder);
        return awardsDto;
    }
}
