package com.egs.task.atmemulator.service;

import com.egs.task.atmemulator.exeption.NotFoundException;
import com.egs.task.atmemulator.model.ATMUser;
import com.egs.task.atmemulator.repository.ATMUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserBalanceServiceImpl implements UserBalanceService {

    private final ATMUserRepository atmUserRepository;

    @Override
    @Transactional
    public Long updateUserBalance(String email, Long incomingCash) {
        Optional<ATMUser> atmUserById = atmUserRepository.findATMUserByEmail(email);
        if (atmUserById.isEmpty()) {
            throw new NotFoundException("user not found");
        }
        try {
            atmUserRepository.addToUserBalance(atmUserById.get().getId(), incomingCash);
        } catch (DataIntegrityViolationException exception) {
            throw new ValidationException();
        }
        return atmUserById.get().getBalance() + incomingCash;

    }

    @Override
    @Transactional()
    public Long cashOut(String email, Long cash) {
        Optional<ATMUser> atmUserById = atmUserRepository.findATMUserByEmail(email);
        if (atmUserById.isEmpty()) {
            throw new NotFoundException("user not found");
        }
        try {
            atmUserRepository.reduceUserBalance(atmUserById.get().getId(), cash);
        } catch (DataIntegrityViolationException exception) {
            throw new ValidationException();
        }
        return atmUserById.get().getBalance() - cash;
    }

}
