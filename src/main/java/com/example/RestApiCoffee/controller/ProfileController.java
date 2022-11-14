package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.dto.responses.UpdateUserResponseWithNewJwt;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.dto.responses.MessageResponse;
import com.example.RestApiCoffee.dto.requests.user.PasswordRequest;
import com.example.RestApiCoffee.dto.requests.user.PhoneRequest;
import com.example.RestApiCoffee.dto.requests.user.UpdateUserRequest;
import com.example.RestApiCoffee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/profile")
@AllArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if(user == null){
            return new ResponseEntity<>(new MessageResponse("user is null"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user.buildUserProfileDto(), HttpStatus.OK);
    }

    @GetMapping("/checkPhone")
    public ResponseEntity<?> checkPhone(
            @RequestBody PhoneRequest phoneRequest
            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if(user == null){
            return new ResponseEntity<>(new MessageResponse("user is null"), HttpStatus.BAD_REQUEST);
        }
        if(phoneRequest == null){
            return new ResponseEntity<>(new MessageResponse("phone is null"), HttpStatus.BAD_REQUEST);
        }
        if(userService.checkPhone(user, phoneRequest.getPhone())){
            return new ResponseEntity<>(new MessageResponse("phone is already registered"), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/checkPassword")
    public ResponseEntity<?> checkPassword(
            @RequestBody PasswordRequest passwordRequest
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if(user == null){
            return new ResponseEntity<>(new MessageResponse("user is null"), HttpStatus.BAD_REQUEST);
        }
        if(passwordRequest == null){
            return new ResponseEntity<>(new MessageResponse("phone is null"), HttpStatus.BAD_REQUEST);
        }
        if (userService.checkPassword(user, passwordRequest.getPassword())){
            return new ResponseEntity<>(new MessageResponse("wrong password"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MessageResponse("correct password"), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateProfile(
            @Valid @RequestBody UpdateUserRequest updateUserRequest,
            BindingResult bindingResult
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if(updateUserRequest == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userService.checkPhone(user, updateUserRequest.getPhone())){
            bindingResult.addError(new FieldError("updateUserRequest", "phone", "phone is already registered"));
        }
        if (updateUserRequest.getPassword() != null) {
            if (userService.checkPassword(user, updateUserRequest.getPassword())) {
                bindingResult.addError(new FieldError("updateUserRequest","password", "wrong password"));
            }
        }
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        userService.update(user.getId(), updateUserRequest.buildUser());

        if(!updateUserRequest.getPhone().equals(user.getPhone())){
            String jwt = userService.createNewJwt(
                    updateUserRequest.getPhone(),
                    updateUserRequest.getPassword());
            UpdateUserResponseWithNewJwt response = updateUserRequest.buildJwtResponse();
            response.setToken(jwt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(updateUserRequest, HttpStatus.OK);
    }


}
