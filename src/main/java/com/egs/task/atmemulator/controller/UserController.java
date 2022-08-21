package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.config.custom.CustomUser;
import com.egs.task.atmemulator.dto.ATMUserSignUpDTO;
import com.egs.task.atmemulator.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.UUID;
/**
 * The UserController api implements functionality to manage user
 * card information
 *
 * @author  Sargis Minasyan
 * @version 1.0
 * @since   2022-08-21
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserFacade userFacade;
    @RequestMapping(value = "/get/user/balance" ,method = RequestMethod.GET,name ="Check balance")
    public ResponseEntity getUserBalance(Authentication authentication){
        return new ResponseEntity<>(((CustomUser) authentication.getPrincipal()).getBalance(),HttpStatus.FOUND);
    }

    @RequestMapping(value = "/get/user/messages" ,method = RequestMethod.GET,name ="Check balance")
    public ResponseEntity getUserMessages(Authentication authentication){
        return new ResponseEntity<>(userFacade.findMessages(((CustomUser) authentication.getPrincipal()).getUuid()),HttpStatus.FOUND);
    }

    @RequestMapping(value = "/cash/out"  ,method = RequestMethod.POST,name = "Cash withdrawal")
    public ResponseEntity cashOut(@RequestParam @Min(1000) @Max(10000) Long cashOut, Authentication authentication){
        return new ResponseEntity<>(userFacade.cashOut(((CustomUser) authentication.getPrincipal()).getEmail(),cashOut),HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/fill/balance"  ,method = RequestMethod.POST)
    public ResponseEntity fillBalance(@RequestParam @Min(1000) @Max(1000000) Long incomingCash, Authentication authentication){
        return new ResponseEntity<>(userFacade.fillBalance(((CustomUser) authentication.getPrincipal()).getEmail(),incomingCash),HttpStatus.ACCEPTED);
    }


//    @GetMapping(name = "/update/user/balance" )
//    public CustomerDTO updateUserBalance(@PathVariable String name){
//        return userFacade.findCustomerByName(name);
//    }
//    @GetMapping(name = "/find/customer/by/{name)" )
//    public CustomerDTO findCustomerByName(@PathVariable String name){
//        return customerFacade.findCustomerByName(name);
//    }
//    @GetMapping(name = "/find/customer/by/{name)" )
//    public CustomerDTO findCustomerByName(@PathVariable String name){
//        return customerFacade.findCustomerByName(name);
//    }
}
