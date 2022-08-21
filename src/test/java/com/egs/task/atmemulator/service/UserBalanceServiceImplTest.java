package com.egs.task.atmemulator.service;

import com.egs.task.atmemulator.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserBalanceServiceImplTest {
    @Autowired
    UserBalanceService userBalanceService;
    @Test
    void updateUserBalance() {
        userBalanceService.updateUserBalance("sargis.minasyan@mail.ru", 15678L);
    }

    @Test
    void cashOut() {
    }
}