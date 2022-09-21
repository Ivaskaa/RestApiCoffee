package com.example.RestApiCoffee.entities.product.tea;

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
@Table(name = "teas")
public class Tea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Title shouldn't be empty")
    @Size(max = 255, message = "Title should be less than 255 characters")
    private String name;
    private String photo;
    @NotEmpty(message = "Description shouldn't be empty")
    @Size(max = 255, message = "Description should be less than 255 characters")
    private String description;

    private boolean active;

    @JoinColumn(name = "tea_id")
    @OneToMany(
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = {CascadeType.ALL}
    )
    private Set<TeaSize> sizes;

    @JoinColumn(name = "tea_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<TeaOrder> teaOrders;

    @Override
    public String toString() {
        return "Tea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", sizes=" + sizes +
                '}';
    }
}
