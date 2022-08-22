package com.egs.task.atmemulator.repository;

import com.egs.task.atmemulator.model.ATMUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ATMUserRepository extends JpaRepository<ATMUser, Long> {

    @Query("SELECT user from ATMUser as user where user.email = ?1")
    Optional<ATMUser> findATMUserByEmail(String email);
    @Query("SELECT user from ATMUser as user where user.card_number = ?1")
    Optional<ATMUser> findATMUserByCardNumber(String cardNumber);
    Optional<ATMUser> findATMUserById(Long id);
    @Query("SELECT user.balance from ATMUser as user where user.email = ?1")
    Optional<String> findBalanceByEmail(String email);

    @Modifying
    @Query("UPDATE ATMUser as user set  user.balance = user.balance + :addTo where user.id = :id ")
    void addToUserBalance(@Param(value = "id") Long id, @Param(value = "addTo") Long addTo);

    @Modifying
    @Query("UPDATE ATMUser as user set  user.balance = user.balance - :addTo where user.id = :id ")
    void reduceUserBalance(@Param(value = "id")Long id , @Param(value = "addTo") Long addTo);


}
