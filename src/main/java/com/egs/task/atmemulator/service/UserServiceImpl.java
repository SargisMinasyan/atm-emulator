package com.egs.task.atmemulator.service;

import com.egs.task.atmemulator.dto.ATMUserSignUpDTO;
import com.egs.task.atmemulator.exeption.NotFoundException;
import com.egs.task.atmemulator.model.ATMUser;
import com.egs.task.atmemulator.model.CommunicationNetwork;
import com.egs.task.atmemulator.model.MessagesForATMUser;
import com.egs.task.atmemulator.model.UserRole;
import com.egs.task.atmemulator.repository.ATMUserRepository;
import com.egs.task.atmemulator.repository.CommunicationRepository;
import com.egs.task.atmemulator.repository.MessagesRepository;
import com.egs.task.atmemulator.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ATMUserRepository atmUserRepository;
    private final MessagesRepository messagesRepository;
    private final CommunicationRepository communicationRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public Optional<ATMUser> findByEmail(String username) {
        log.info("Searching user by username {}", username);
        return atmUserRepository.findATMUserByEmail(username);
    }

    @Override
    public Optional<ATMUser> findByCardNumber(String cardNumber) {
        log.info("Searching user by cartNumber {}", cardNumber);
        return atmUserRepository.findATMUserByCardNumber(cardNumber);
    }

    @Override
    public String findBalanceByEmail(String username) {
        return null;
    }

    @Override
    public ATMUser save(ATMUserSignUpDTO user) {
        log.info("Saving user by username {}", user.getEmail());
        var roleByRoleName = userRoleRepository.findRoleByRoleName(UserRole.ROLE_USER);
        return atmUserRepository.save(ATMUser.of(user.getEmail(), user.getPassword(),
                user.getCard_holder_name(), user.getCard_number(), user.getCard_cvv(), user.getCardExpDate(), roleByRoleName, user.getBalance()));


    }

    @Override
    public List<CommunicationNetwork> findCommunications() {
        return communicationRepository.findAll();
    }

    @Override
    public List<MessagesForATMUser> findAllUserMessages(UUID uuid) {
        return messagesRepository.findAllByUserUUID(uuid);
    }

    @Override
    @Transactional
    public MessagesForATMUser addMessages(String cardNumber, String message, String title) {
        Optional<ATMUser> atmUser = atmUserRepository.findATMUserByCardNumber(cardNumber);
        MessagesForATMUser save;
        if (atmUser.isPresent()) {
            save = messagesRepository.save(MessagesForATMUser.of(message, title, atmUser.get()));
        } else {
            throw new NotFoundException(cardNumber);
        }
        return save;
    }


}
