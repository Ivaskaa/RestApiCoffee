package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.dto.responses.UpdateUserResponseWithNewJwt;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.dto.responses.MessageResponse;
import com.example.RestApiCoffee.dto.requests.user.PasswordRequest;
import com.example.RestApiCoffee.dto.requests.user.PhoneRequest;
import com.example.RestApiCoffee.dto.requests.user.UpdateUserRequest;
import com.example.RestApiCoffee.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class ProfileController {
    private final UserService userService;

    @ApiOperation(value = "Get profile", notes = "Get user profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - User profile is empty")
    })
    @GetMapping()
    public ResponseEntity<?> getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if(user == null){
            return new ResponseEntity<>(new MessageResponse("User profile is empty"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.buildUserProfileDto(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Check unique phone",
            notes = "Check unique phone (if the user writes the phone on which he is already registered, it will return Http status OK)" +
                    " this method only for validation (in method update profile this validation is also performed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Phone that is not registered (passed validation)"),
            @ApiResponse(code = 302, message = "Found - Phone is already registered"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly")
    })
    @GetMapping("/checkPhone")
    public ResponseEntity<?> checkPhone(
            @Valid @RequestBody PhoneRequest phoneRequest,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if(userService.checkPhone(user, phoneRequest.getPhone())){
            return new ResponseEntity<>(new MessageResponse("Phone is already registered"), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Check user password",
            notes = "Check user password this method only for validation " +
                    "(in method update profile this validation is also performed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Correct password (passed validation)"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - Wrong password")
    })
    @GetMapping("/checkPassword")
    public ResponseEntity<?> checkPassword(
            @Valid @RequestBody PasswordRequest passwordRequest,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if (userService.checkPassword(user, passwordRequest.getPassword())){
            return new ResponseEntity<>(new MessageResponse("Wrong password"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new MessageResponse("Correct password"), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update profile",
            notes = "Update user profile in this method, we can get two different responses with Http status OK " +
                    "(if the phone number is changed, a new access token will be returned for the user, " +
                    "otherwise the access token will not be returned)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Correct password (passed validation)"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly")
    })
    @PutMapping()
    public ResponseEntity<?> updateProfile(
            @Valid @RequestBody UpdateUserRequest updateUserRequest,
            BindingResult bindingResult
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if (userService.checkPhone(user, updateUserRequest.getPhone())){
            bindingResult.addError(new FieldError("updateUserRequest", "phone", "Phone is already registered"));
        }
        if (updateUserRequest.getPassword() != null) {
            if (userService.checkPassword(user, updateUserRequest.getPassword())) {
                bindingResult.addError(new FieldError("updateUserRequest","password", "Wrong password"));
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
