package com.example.RestApiCoffee.entities.user;

import com.example.RestApiCoffee.entities.Location;
import com.example.RestApiCoffee.entities.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String name;
    private String password;
    @JoinColumn(name = "location_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private Location location;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonManagedReference
    private Set<Role> roles;
    private String registrationDate;
    private Date birthday;
    private boolean active;
    private Integer points;

    public void todayRegistrationDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        this.registrationDate = format.format(date);
    }

    public UserProfileDto buildUserProfileDto(){
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(this.id);
        userProfileDto.setName(this.name);
        userProfileDto.setBirthday(this.birthday);
        userProfileDto.setPhone(this.phone);
        userProfileDto.setPoints(this.points);
        return userProfileDto;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", roles=" + roles +
                ", registrationDate=" + registrationDate +
                ", birthday=" + birthday +
                ", active=" + active +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
