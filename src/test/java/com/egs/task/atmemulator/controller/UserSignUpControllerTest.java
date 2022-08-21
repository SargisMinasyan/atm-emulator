package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.dto.ATMUserSignUpDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserSignUpControllerTest {

    @Autowired
    private UserSignUpController userSignUpController;
    @Test
    void createNewUser() {
        assertEquals(HttpStatus.CREATED,userSignUpController.createNewUser(new ATMUserSignUpDTO()).getStatusCode());
    }
}