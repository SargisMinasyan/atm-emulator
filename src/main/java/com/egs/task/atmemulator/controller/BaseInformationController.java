package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The BaseInformationController api implements functionality to display customer support information
 *
 * @author  Sargis Minasyan
 * @version 1.0
 * @since   2022-08-21
 */
@RestController
@RequestMapping("/base_information")
public class BaseInformationController {
    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/communication_network", method = RequestMethod.GET, name = "communication network")
    public ResponseEntity getUserBalance() {
        return new ResponseEntity<>(userFacade.findCommunications(), HttpStatus.FOUND);
    }
    @RequestMapping(value = "/add/new/message", method = RequestMethod.GET, name = "communication network")
    public ResponseEntity addMessageByCardNumber(@RequestParam(name = "userCardNumber") String userCardNumber,
                                                 @RequestParam(name = "message") String message,
                                                 @RequestParam(name = "title") String title) {
        userFacade.addMessages(userCardNumber,message,title);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
