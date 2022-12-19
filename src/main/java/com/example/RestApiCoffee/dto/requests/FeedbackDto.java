package com.example.RestApiCoffee.dto.requests;

import com.example.RestApiCoffee.entities.Feedback;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class FeedbackDto {
    @NotBlank(message = "Name shouldn't be empty")
    @Size(max = 255, message = "Name should be less than 255 characters")
    private String name;

    @NotBlank(message = "Phone shouldn't be empty")
    @Size(max = 255, message = "Phone should be less than 255 characters")
    private String phone;

    @NotBlank(message = "Email shouldn't be empty")
    @Email(message = "Email must be valid")
    @Size(max = 255, message = "Email should be less than 255 characters")
    private String email;

    public Feedback build(){
        Feedback feedback = new Feedback();
        feedback.setName(name);
        feedback.setPhone(phone);
        feedback.setEmail(email);
        feedback.setStatus("Unwatched");
        return feedback;
    }
}
