package com.egs.task.atmemulator.repository;

import com.egs.task.atmemulator.model.MessagesForATMUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessagesRepository extends JpaRepository<MessagesForATMUser, Long> {
    @Query("SELECT messages from MessagesForATMUser as messages left join ATMUser as user on messages.user.id = user.id and  user.uuid = :uuid")
    List<MessagesForATMUser> findAllByUserUUID(@Param("uuid") UUID uuid);

}
