package com.miniproject.miniproject.web.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.miniproject.miniproject.data.Dto.ApiResponse;
import com.miniproject.miniproject.data.Dto.ResponseDto;
import com.miniproject.miniproject.data.Dto.UserDTO;
import com.miniproject.miniproject.data.model.User;
import com.miniproject.miniproject.service.user.UserService;
import com.miniproject.miniproject.web.exceptions.TokenException;
import com.miniproject.miniproject.web.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) throws UnirestException {
        try {
            ResponseDto responseDto = userService.registerUser(userDTO);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (TokenException tokenException) {
            return new ResponseEntity<>(
                    new ApiResponse(tokenException.getMessage(),
                            LocalDateTime.now(), false), HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) throws UserNotFoundException {

        try {
            User user = userService.updateUser(userDTO, id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            log.info("Error Occured --> {}", exception.getMessage());
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
