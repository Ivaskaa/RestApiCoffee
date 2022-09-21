package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.config.jwt.JwtUtils;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.dto.responses.JwtResponse;
import com.example.RestApiCoffee.dto.requests.user.LoginRequest;
import com.example.RestApiCoffee.dto.responses.MessageResponse;
import com.example.RestApiCoffee.dto.requests.user.SignupRequest;
import com.example.RestApiCoffee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String jwt = userService.createNewJwt(
                loginRequest.getPhone(),
                loginRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        User user = userService.register(signupRequest);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
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
