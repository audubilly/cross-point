package com.miniproject.miniproject.data.repository;

import com.miniproject.miniproject.data.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveUserToDbTest(){
        User user = new User();
        user.setId("1");
        user.setFirstName("john");
        user.setLastName("doe");
        user.setPhoneNumber("+2349056790444");
        log.info("created a user --> {}",user);
        userRepository.save(user);

        assertThat(user.getId()).isNotNull();
    }

//    @Test
//    void updateEmployeeRecordTest(){
//        User user = userRepository.findById("12253435hchwcvdcyew").orElse(null);
//    }


}