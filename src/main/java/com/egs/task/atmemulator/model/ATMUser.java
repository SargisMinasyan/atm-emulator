package com.egs.task.atmemulator.model;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "atm_user")
public class ATMUser extends BaseEntity implements Serializable {
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String card_holder_name;
    @Column(unique = true)
    private String card_number;
    private String card_cvv;
    private LocalDateTime cardExpDate;
    private Long balance;

    private Boolean deactivated;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    protected ATMUser() {
    }

    protected ATMUser(String email, String password, String card_holder_name, String card_number, String card_cvv, LocalDateTime cardExpDate, Role role, Long balance) {
        this.email = email;
        this.password = password;
        this.card_holder_name = card_holder_name;
        this.card_number = card_number;
        this.card_cvv = card_cvv;
        this.cardExpDate = cardExpDate;
        this.role = role;
        this.balance = balance;
        this.deactivated = false;
    }

    public ATMUser(@NonNull ATMUser atmUser) {
        this.email = atmUser.getEmail();
        this.password = atmUser.getPassword();
        this.card_holder_name = atmUser.getCard_holder_name();
        this.card_number = atmUser.getCard_number();
        this.card_cvv = atmUser.getCard_cvv();
        this.cardExpDate = atmUser.getCardExpDate();
        this.role = atmUser.getRole();
        this.balance = atmUser.getBalance();
    }

    public static ATMUser of(final String email, final String password, final String card_holder_name
            , final String card_number, final String card_cvv, final LocalDateTime cardExpDate, final Role userRole, final Long balance) {
        return new ATMUser(email, password, card_holder_name, card_number, card_cvv, cardExpDate, userRole, balance);
    }

    public void changeBalance(final Long balance) {
        this.balance = getBalance() + balance;
    }

    public void changeBalanceCashOut(final Long balance) {
        if (getBalance() - balance < 0) {
            throw new UnsupportedOperationException();
        }
        this.balance = getBalance() - balance;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }

}