package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.dto.requests.user.LoginRequest;
import com.example.RestApiCoffee.dto.requests.user.SignupRequest;
import com.example.RestApiCoffee.dto.responses.JwtResponse;
import com.example.RestApiCoffee.dto.responses.MessageResponse;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error")
})
public class AuthController {
    private final UserService userService;


    @ApiOperation(value = "Login user", notes = "Login user by phone and password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully login"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly"),
            @ApiResponse(code = 401, message = "Unauthorized - Invalid user credentials"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - The user with phone was not found")
    })
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @ApiParam(name = "Login", value = "Login request")
            @Valid @RequestBody LoginRequest loginRequest,
            BindingResult bindingResult
    ) {
        if(userService.findByPhone(loginRequest.getPhone()) == null){
            return new ResponseEntity<>(new MessageResponse("User with phone already created"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        String jwt = userService.createNewJwt(
                loginRequest.getPhone(),
                loginRequest.getPassword());
        return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
    }

    @ApiOperation(value = "Registration user", notes = "Registration user by phone, password, and name(username)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully registered"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - User with phone already created")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
            @ApiParam(name = "Registration", value = "Registration request")
            @Valid @RequestBody SignupRequest signupRequest,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        User user = userService.register(signupRequest);
        if(user == null){
            return new ResponseEntity<>(new MessageResponse("User with phone already created"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new MessageResponse("User created"), HttpStatus.OK);
    }

//    @PostMapping("/forgotPassword")
//    public ResponseEntity<?> forgotPassword(String phone) {
//
//        User user = userService.findByPhone(phone);
//
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        //create code
//
//        //send sms code to this phone;
//
//        //save code in user table
//
//        return ResponseEntity.ok(new MessageResponse("Code was send"));
//    }
//
//    @PostMapping("/forgotPasswordCode")
//    public ResponseEntity<?> forgotPasswordCode(
//            String code,
//            String phone
//    ) {
//        User user = userService.findByPhone(phone);
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
////        if(!user.getCode().equals(code)){
////            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("/changePassword")
//    public ResponseEntity<?> changePassword(
//            String newPassword,
//            String phone
//    ) {
//        User user = userService.findByPhone(phone);
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        userService.changePassword(user, newPassword);
//        // clear code
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
