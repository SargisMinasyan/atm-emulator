package com.egs.task.atmemulator.config.security;


import com.egs.task.atmemulator.config.custom.CustomUser;
import com.egs.task.atmemulator.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
/**
 * The ServiceUserDetailsService withe custom user creation to have own user in security side
 *
 * @author  Sargis Minasyan
 * @version 1.0
 * @since   2022-08-21
 */
@Slf4j
@Component
@AllArgsConstructor
public class ServiceUserDetailsService implements UserDetailsService {

    private final UserService users;
    private final HttpSession httpSession;
    @Override
    public UserDetails loadUserByUsername(final String cardNumber) throws UsernameNotFoundException {
        httpSession.setAttribute("name",cardNumber);
        var walletUser = users.findByCardNumber(cardNumber).orElseThrow(() -> {
            log.error("Can`t authorize user. cardNumber {} not found.", cardNumber);
            return new UsernameNotFoundException("Can`t authorize user. cardNumber not found." + cardNumber);
        });
        if (walletUser.getDeactivated()) {
            log.error("Can`t authorize user. User is deactivated", cardNumber);
            throw new UsernameNotFoundException("Can`t authorize user. User is deactivated " + cardNumber);
        }
        return new CustomUser(walletUser);
    }
}