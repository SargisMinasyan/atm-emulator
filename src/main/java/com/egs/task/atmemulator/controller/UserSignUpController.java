package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.dto.ATMUserSignUpDTO;
import com.egs.task.atmemulator.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserSignUpController {
    @Autowired
    private UserFacade userFacade;
    @RequestMapping(value = "/signup"  ,method = RequestMethod.POST)
    public ResponseEntity createNewUser(@RequestBody ATMUserSignUpDTO atmUserSignUpDTO){
        userFacade.createNewATMUser(atmUserSignUpDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
