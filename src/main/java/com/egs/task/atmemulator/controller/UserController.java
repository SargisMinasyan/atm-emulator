package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.config.custom.CustomUser;
import com.egs.task.atmemulator.dto.MessageDTO;
import com.egs.task.atmemulator.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * The UserController api implements functionality to manage user
 * card information
 *
 * @author Sargis Minasyan
 * @version 1.0
 * @since 2022-08-21
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserFacade userFacade;

    @RequestMapping(value = "/get/user/balance", method = RequestMethod.GET, name = "Check balance")
    public ResponseEntity<Long> getUserBalance(Authentication authentication) {
        return new ResponseEntity<>(((CustomUser) authentication.getPrincipal()).getBalance(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/get/user/messages", method = RequestMethod.GET, name = "Check balance")
    public ResponseEntity<List<MessageDTO>> getUserMessages(Authentication authentication) {
        return new ResponseEntity<>(userFacade.findMessages(((CustomUser) authentication.getPrincipal()).getUuid()), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/cash/out", method = RequestMethod.POST, name = "Cash withdrawal")
    public ResponseEntity<Long> cashOut(@RequestParam @Min(1000) @Max(10000) Long cashOut, Authentication authentication) {
        return new ResponseEntity<>(userFacade.cashOut(((CustomUser) authentication.getPrincipal()).getEmail(), cashOut), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/fill/balance", method = RequestMethod.POST)
    public ResponseEntity<Long> fillBalance(@RequestParam @Min(1000) @Max(1000000) Long incomingCash, Authentication authentication) {
        return new ResponseEntity<>(userFacade.fillBalance(((CustomUser) authentication.getPrincipal()).getEmail(), incomingCash), HttpStatus.ACCEPTED);
    }

}
