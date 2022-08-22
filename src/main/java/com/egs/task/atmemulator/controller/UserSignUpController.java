package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.dto.ATMUserSignUpDTO;
import com.egs.task.atmemulator.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The BaseInformationController api implements functionality to register new user
 *
 * @author  Sargis Minasyan
 * @version 1.0
 * @since   2022-08-21
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserSignUpController {
    private final UserFacade userFacade;

    @RequestMapping(value = "/signup"  ,method = RequestMethod.POST)
    public ResponseEntity<Object> createNewUser(@RequestBody ATMUserSignUpDTO atmUserSignUpDTO){
        userFacade.createNewATMUser(atmUserSignUpDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
