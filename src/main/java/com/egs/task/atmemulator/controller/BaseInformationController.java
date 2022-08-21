package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    public ResponseEntity communicationNetwork() {
        return new ResponseEntity<>(userFacade.findCommunications(), HttpStatus.FOUND);
    }
    @RequestMapping(value = "/add/new/message", method = RequestMethod.POST, name = "communication network")
    public ResponseEntity addMessageByCardNumber(@RequestParam(name = "userCardNumber") String userCardNumber,
                                                 @RequestParam(name = "message") @NotBlank @Size(min = 10,max = 600) String message,
                                                 @RequestParam(name = "title") @NotBlank @Size(min = 10,max = 20) String title) {

        return new ResponseEntity<>( userFacade.addMessages(userCardNumber,message,title),HttpStatus.CREATED);
    }


}
