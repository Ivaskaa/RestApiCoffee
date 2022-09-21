package com.example.RestApiCoffee.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(max = 255, message = "Name should be less than 255 characters")
    private String name;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(max = 20, message = "Name should be less than 255 characters")
    private String latitude; // широта
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(max = 20, message = "Name should be less than 255 characters")
    private String longitude; // довгота
    private boolean active;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", active=" + active +
                '}';
    }
}
