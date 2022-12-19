package com.example.RestApiCoffee.dto.requests.user;

import com.example.RestApiCoffee.dto.responses.UpdateUserResponseWithNewJwt;
import com.example.RestApiCoffee.entities.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "Phone should`t be empty")
    @ApiModelProperty(notes = "User phone number", example = "0973423583", required = true)
    private String phone;
    @ApiModelProperty(notes = "User name", example = "name")
    private String name;

    @NotEmpty(message = "Password should`t be empty")
    @Size(min = 8, max = 255, message = "Must be rather then 8 and less then 255 symbols")
    @ApiModelProperty(notes = "User password", example = "password", required = true)
    private String password;
    @ApiModelProperty(notes = "New User password", example = "newPassword")
    private String newPassword;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @ApiModelProperty(notes = "User birthday", example = "DD.MM.YYYY")
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
