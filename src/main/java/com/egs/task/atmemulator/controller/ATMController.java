package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.config.custom.CustomUser;
import com.egs.task.atmemulator.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Controller
@RequestMapping(value = "/atm")
public class ATMController {
    @Autowired
    UserFacade userFacade;

    @GetMapping("/")
    public String atmPage() {
        return "/atm";
    }

    @GetMapping("/withDrawl")
    public ModelAndView withDrawl(Authentication authentication) {
        ModelAndView withDrawl = new ModelAndView();
        BigDecimal currentBalance = BigDecimal.valueOf(((CustomUser) authentication.getPrincipal()).getBalance());
        withDrawl.setViewName("withDrawl");
        withDrawl.addObject("currentBalance", currentBalance);
        return withDrawl;
    }

    @PostMapping("/withDrawl")
    public ModelAndView withDrawl(@RequestParam(value = "withDrawlAmount", required = true) String withDrawlAmount, Authentication authentication) {
        BigDecimal currentBalance = BigDecimal.valueOf(Long.parseLong(withDrawlAmount));
        userFacade.cashOut(((CustomUser) authentication.getPrincipal()).getEmail(),currentBalance.longValue());
        ModelAndView successCash = new ModelAndView();
        successCash.setViewName("successCash");
        successCash.addObject("withDrawlAmount",withDrawlAmount);
       return successCash;
    }


}
