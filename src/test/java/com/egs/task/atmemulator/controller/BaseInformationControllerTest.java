package com.egs.task.atmemulator.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BaseInformationControllerTest {
    @Autowired
    private BaseInformationController baseInformationController;
    @Test
    void communicationNetwork() {
        assertEquals(HttpStatus.FOUND,baseInformationController.communicationNetwork().getStatusCode());
    }


    @Test
    void addMessageByCardNumber() {
        assertEquals(HttpStatus.CREATED,baseInformationController.addMessageByCardNumber("115","test message","test title").getStatusCode());

    }
}