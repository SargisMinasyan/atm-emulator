package com.egs.task.atmemulator.controller;

import com.egs.task.atmemulator.config.custom.CustomUser;
import com.egs.task.atmemulator.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping(value = "/atm")
@AllArgsConstructor
public class ATMController {
    private final UserFacade userFacade;
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
    @GetMapping("/fill")
    public ModelAndView fill(Authentication authentication) {
        ModelAndView withDrawl = new ModelAndView();
        BigDecimal currentBalance = BigDecimal.valueOf(((CustomUser) authentication.getPrincipal()).getBalance());
        withDrawl.setViewName("fill");
        withDrawl.addObject("currentBalance", currentBalance);
        return withDrawl;
    }
    @PostMapping("/fill/balance")
    public ModelAndView fillBalance(@RequestParam(value = "fillAmount", required = true) String fillAmount,Authentication authentication) {
        BigDecimal currentBalance = BigDecimal.valueOf(Long.parseLong(fillAmount));
        userFacade.fillBalance(((CustomUser) authentication.getPrincipal()).getEmail(),currentBalance.longValue());
        ModelAndView withDrawl = new ModelAndView();
        withDrawl.setViewName("successFill");
        return withDrawl;
    }
    @PostMapping("/withDrawl")
    public ModelAndView withDrawl(@RequestParam(value = "withDrawlAmount", required = true) String withDrawlAmount, Authentication authentication) {
        BigDecimal currentBalance = BigDecimal.valueOf(Long.parseLong(withDrawlAmount));
        userFacade.cashOut(((CustomUser) authentication.getPrincipal()).getEmail(), currentBalance.longValue());
        ModelAndView successCash = new ModelAndView();
        successCash.setViewName("successCash");
        successCash.addObject("withDrawlAmount", withDrawlAmount);
        return successCash;
    }



}
