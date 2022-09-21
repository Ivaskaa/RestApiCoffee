package com.example.RestApiCoffee.dto.requests.user;

import com.example.RestApiCoffee.dto.responses.UpdateUserResponseWithNewJwt;
import com.example.RestApiCoffee.entities.user.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateUserRequest {

    @NotEmpty(message = "phone should`t be empty")
    private String phone;
    private String name;

    @NotEmpty(message = "password should`t be empty")
    private String password;
    private String newPassword;
    private Date birthday;

    public User buildUser(){
        User user = new User();
        user.setPhone(this.phone);
        user.setName(this.name);
        user.setPassword(this.newPassword);
        user.setBirthday(this.birthday);
        return user;
    }

    public UpdateUserResponseWithNewJwt buildJwtResponse(){
        UpdateUserResponseWithNewJwt user = new UpdateUserResponseWithNewJwt();
        user.setPhone(this.phone);
        user.setName(this.name);
        user.setNewPassword(this.newPassword);
        user.setBirthday(this.birthday);
        return user;
    }
}
