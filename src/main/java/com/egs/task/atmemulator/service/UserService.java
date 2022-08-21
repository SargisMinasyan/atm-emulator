package com.egs.task.atmemulator.service;

import com.egs.task.atmemulator.dto.ATMUserSignUpDTO;
import com.egs.task.atmemulator.model.ATMUser;
import com.egs.task.atmemulator.model.CommunicationNetwork;
import com.egs.task.atmemulator.model.MessagesForATMUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<ATMUser> findByEmail(String username);

    Optional<ATMUser> findByCardNumber(String cartNumber);

    String findBalanceByEmail(String username);

    ATMUser save(ATMUserSignUpDTO user);

    List<CommunicationNetwork> findCommunications();

    List<MessagesForATMUser> findAllUserMessages(UUID uuid);
    void addMessages(String cardNumber, String message,String title);
}
