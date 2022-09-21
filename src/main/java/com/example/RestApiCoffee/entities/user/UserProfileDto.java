package com.example.RestApiCoffee.entities.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserProfileDto {
    private Long id;
    private String phone;
    private String name;
    private String password;
    private Date birthday;
    private Integer points;

    public User build(){
        User user = new User();
        user.setId(this.id);
        user.setPhone(this.phone);
        user.setName(this.name);
        user.setPassword(this.password);
        user.setBirthday(this.birthday);
        user.setPoints(this.points);
        return user;
    }
}
