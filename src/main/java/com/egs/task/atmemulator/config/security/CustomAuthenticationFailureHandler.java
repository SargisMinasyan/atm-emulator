package com.egs.task.atmemulator.config.security;

import com.egs.task.atmemulator.model.ATMUser;
import com.egs.task.atmemulator.repository.ATMUserRepository;
import com.egs.task.atmemulator.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/**
 * The CustomAuthenticationFailureHandler to manage and deactivate user after 3 failure login
 *
 * @author  Sargis Minasyan
 * @version 1.0
 * @since   2022-08-21
 */
public class CustomAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private HttpSession httpSession;

    @Autowired
    ATMUserRepository atmUserRepository;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,HttpServletResponse response,AuthenticationException exception)
            throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put(
                "timestamp",
                Calendar.getInstance().getTime());
        data.put(
                "exception",
                exception.getMessage());

        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
        if (httpSession.getAttribute("name")!=null) {
            String username = (String) httpSession.getAttribute("name");
            if (httpSession.getAttribute(username) != null) {
                if (((Integer) httpSession.getAttribute(username)) < 3) {
                    httpSession.setAttribute(username, ((Integer) httpSession.getAttribute(username)) + 1);
                } else {
                    Optional<ATMUser> atmUserByEmail = atmUserRepository.findATMUserByCardNumber(username);
                    if (!atmUserByEmail.isEmpty()) {
                        ATMUser atmUser = atmUserByEmail.get();
                        atmUser.setDeactivated(true);
                        atmUserRepository.save(atmUser);
                    }
                }
            } else {
                httpSession.setAttribute(username, 1);
            }
        }
    }
}