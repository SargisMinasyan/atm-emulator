package com.egs.task.atmemulator.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WithMockUser(username ="116",password = "1234" ,setupBefore = TestExecutionEvent.TEST_METHOD)
class UserControllerTest {
    @Autowired
    UserController userController;

    ApplicationContext springSessionContext;
    @Test
    void getUserBalance() {

    }

    @Test
    void getUserMessages() {
    }

    @Test
    void cashOut() {
    }

    @Test
    void fillBalance() {
    }
}