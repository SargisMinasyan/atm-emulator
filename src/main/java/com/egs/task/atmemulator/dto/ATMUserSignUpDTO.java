package com.egs.task.atmemulator.dto;

import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ATMUserSignUpDTO {

    private String email;
    private String password;
    private String card_holder_name;
    private String card_number;
    private String card_cvv;
    private LocalDateTime cardExpDate;
    private Long balance;
}