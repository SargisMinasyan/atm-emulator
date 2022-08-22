package com.egs.task.atmemulator.service;

public interface UserBalanceService {

    Long updateUserBalance(String email, Long incomingCash);

    Long cashOut(String email, Long cash);
}
