package com.example.RestApiCoffee.service;


import com.example.RestApiCoffee.config.jwt.JwtUtils;
import com.example.RestApiCoffee.entities.Role;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.repository.RoleRepository;
import com.example.RestApiCoffee.repository.UserRepository;
import com.example.RestApiCoffee.dto.requests.user.SignupRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@Log4j2
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public User register(SignupRequest signupRequest){
        log.info("registered user: {}", signupRequest);
        User searchUser = userRepository.findByPhone(signupRequest.getPhone()).orElse(null);
        if(searchUser != null){
            return null;
        }
        User user = new User();
        user.setPhone(signupRequest.getPhone());
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        user.todayRegistrationDate();
        user.setActive(true);
        userRepository.save(user);
        log.info("success");
        return user;
    }

    public User findByPhone(String phone){
        log.info("find user by phone: {}", phone);
        User user = userRepository.findByPhone(phone).orElse(null);
        if(user == null){
            log.warn("IN findByPhone - user was null");
        }
        log.info("success");
        return user;
    }

    public User update(Long id, User userForm) {
        log.info("update user: {}", userForm);
        User user = userRepository.findById(id).orElseThrow();
        user.setPhone(userForm.getPhone());
        if(userForm.getPassword() != null && !userForm.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        }
        user.setName(userForm.getName());
        user.setBirthday(userForm.getBirthday());
        userRepository.save(user);
        log.info("success");
        return user;
    }

    public void changePassword(User user, String newPassword) {
        log.info("changePassword user: {} change password: {}", user, newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        log.info("success");
    }

    public boolean checkPhone(User user, String phone) {
        // if ok return false
        if(!user.getPhone().equals(phone)){
            User checkUser = findByPhone(phone);
            return checkUser != null;
        }
        return false;
    }

    public boolean checkPassword(User user, String password) {
        // if equals return false
        return !passwordEncoder.matches(password, user.getPassword());
    }

    public String createNewJwt(String phone, String password) {
        log.info("createNewJwt phone: {} password: {}", phone, password);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        phone,
                        password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        log.info("success");
        return jwt;
    }

    public User addPoints(User user, Integer price) {
        log.info("add points user: {} price: {}", user, price);
        Integer points = user.getPoints();
        if(points == null){
            points = 0;
        }
        points += (price / 10);
        user.setPoints(points);
        userRepository.save(user);
        log.info("success");
        return user;
    }

    public boolean subtractPoint(User user, Double price) {
        log.info("subtract points user: {} price: {}", user, price);
        int priceInt = price.intValue();
        if(user.getPoints() == null || user.getPoints() < priceInt){
            return false;
        }
        user.setPoints(user.getPoints() - priceInt);
        userRepository.save(user);
        log.info("success");
        return true;
    }
}
