package com.example.RestApiCoffee.entities.order.tea;

import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.product.tea.Tea;
import com.example.RestApiCoffee.entities.product.tea.TeaSize;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tea_orders")
public class TeaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "tea_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Tea tea;

    @JoinColumn(name = "syrup_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Syrup syrup;

    @JoinColumn(name = "milk_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Milk milk;

    @JoinColumn(name = "supplement_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Supplement supplement;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private TeaSize size;

    private Integer count;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonBackReference
    private Order order;

    private boolean active;

    @Override
    public String toString() {
        return "TeaOrder{" +
                "id=" + id +
                ", tea=" + tea +
                ", syrup=" + syrup +
                ", milk=" + milk +
                ", supplement=" + supplement +
                ", size=" + size +
                ", count=" + count +
                '}';
    }
}
