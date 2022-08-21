package com.egs.task.atmemulator.service;

import javax.transaction.Transactional;
import java.util.UUID;

public interface UserBalanceService {

    void updateUserBalance(String email, Long incomingCash);

    Long cashOut( String email ,Long cash);
}
