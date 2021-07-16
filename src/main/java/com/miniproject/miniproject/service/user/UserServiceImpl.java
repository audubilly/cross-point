package com.miniproject.miniproject.service.user;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.miniproject.miniproject.data.Dto.ResponseDto;
import com.miniproject.miniproject.data.Dto.UserDTO;
import com.miniproject.miniproject.data.model.User;
import com.miniproject.miniproject.data.repository.UserRepository;
import com.miniproject.miniproject.service.MailGun.MailSender;
import com.miniproject.miniproject.service.SmsToken.TokenSender;
import com.miniproject.miniproject.service.util.UserMapper;
import com.miniproject.miniproject.web.exceptions.TokenException;
import com.miniproject.miniproject.web.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    UserMapper userMapper;

    @Autowired
    private TokenSender tokenSender;

    @Autowired
    private MailSender mailSender;

    public UserServiceImpl() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Override
    public ResponseDto registerUser(UserDTO userDTO) throws TokenException, UnirestException {
        log.info("user Dto ---> {}", userDTO);
        User newUser = modelMapper.map(userDTO, User.class);
        log.info("user obj -->{}", newUser);
        String verificationCode = UUID.randomUUID().toString();
        newUser.setVerificationCode(verificationCode);
        User savedUser = userRepository.save(newUser);
        tokenSender.sendToken(savedUser);
        ResponseDto response = new ResponseDto();
        response.setUserId(savedUser.getId());
        response.setVerificationCode(verificationCode);
        mailSender.sendSimpleMessage();
        log.info("email send --> {}", mailSender.sendSimpleMessage());
        return response;

    }



//
//    @Override
//    public User save(UserDTO userDTO) {
//
//        User user = new User();
//        modelMapper.map(userDTO, user);
//        log.info("user after mapping --> {}", user);
//        return userRepository.save(user);
//    }


    @Override
    public UserDTO findById(String id) throws UserNotFoundException {
        User user = getUserIfExists(id);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }


    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = users.stream().map(user -> modelMapper.map(
                user, UserDTO.class
        )).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void deleteById(String id) throws UserNotFoundException {
        if (id == null) {
            throw new NullPointerException("user id cannot be null");
        }
        User user = getUserIfExists(id);
        userRepository.delete(user);
    }

    private User getUserIfExists(String id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user found with this id"));

    }

    @Override
    public User updateUser(UserDTO userDTO, String id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("user not found");
        }
        userMapper.updateUserFromDto(userDTO, user);
        log.info("user after mapping --> {}", user);
        return userRepository.save(user);
    }

}
