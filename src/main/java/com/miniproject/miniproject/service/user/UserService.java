package com.miniproject.miniproject.service.user;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.miniproject.miniproject.data.Dto.ResponseDto;
import com.miniproject.miniproject.data.Dto.UserDTO;
import com.miniproject.miniproject.data.model.User;
import com.miniproject.miniproject.web.exceptions.TokenException;
import com.miniproject.miniproject.web.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    ResponseDto registerUser(UserDTO userDTO) throws TokenException, UnirestException;

//    User save(UserDTO userDTO);

    UserDTO findById(String id) throws UserNotFoundException;

    List<UserDTO> findAll();

    void deleteById(String id) throws UserNotFoundException;

    User updateUser (UserDTO userDTO, String id) throws UserNotFoundException;

//    void updateUser(UserDTO userToUpdate, String id) throws UserNotFoundException;
}
