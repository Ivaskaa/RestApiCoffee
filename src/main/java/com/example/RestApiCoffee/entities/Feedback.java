package com.example.RestApiCoffee.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(max = 255, message = "Name should be less than 255 characters")
    private String name;

    @NotEmpty(message = "Phone shouldn't be empty")
    @Size(max = 255, message = "Phone should be less than 255 characters")
    private String phone;

    @NotEmpty(message = "Email shouldn't be empty")
    @Size(max = 255, message = "Email should be less than 255 characters")
    private String email;

    private String status;

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
