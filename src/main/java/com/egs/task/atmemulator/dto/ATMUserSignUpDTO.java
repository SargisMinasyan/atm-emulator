package com.egs.task.atmemulator.dto;

import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ATMUserSignUpDTO {

    @NonNull
    @Pattern( regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String card_holder_name;
    @NonNull
    private String card_number;
    @NonNull
    private String card_cvv;
    @NonNull
    private LocalDateTime cardExpDate;
    @NonNull
    private Long balance;
}