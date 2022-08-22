package com.egs.task.atmemulator.facade;

import com.egs.task.atmemulator.dto.ATMUserSignUpDTO;
import com.egs.task.atmemulator.dto.CommunicationNetworkDTO;
import com.egs.task.atmemulator.dto.MessageDTO;
import com.egs.task.atmemulator.service.UserBalanceService;
import com.egs.task.atmemulator.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserFacade {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserBalanceService userBalanceService;
    private final ModelMapper modelMapper;


    public void createNewATMUser(ATMUserSignUpDTO atmUserSignUpDTO) {
        atmUserSignUpDTO.setPassword(passwordEncoder.encode(atmUserSignUpDTO.getPassword()));
        userService.save(atmUserSignUpDTO);
    }

    public Long cashOut(String email, Long cash) {
        return userBalanceService.cashOut(email, cash);
    }

    public Long fillBalance(String email, Long cash) {
        return userBalanceService.updateUserBalance(email, cash);
    }

    public List<CommunicationNetworkDTO> findCommunications() {
        return userService.findCommunications().stream().map(communicationNetwork -> modelMapper.map(communicationNetwork, CommunicationNetworkDTO.class)).collect(Collectors.toList());
    }

    public List<MessageDTO> findMessages(UUID uuid) {
        return userService.findAllUserMessages(uuid).stream().map(messages -> modelMapper.map(messages, MessageDTO.class)).collect(Collectors.toList());
    }

    public MessageDTO addMessages(String cardNumber, String message, String title) {
        return modelMapper.map(userService.addMessages(cardNumber, message, title), MessageDTO.class);
    }
}
