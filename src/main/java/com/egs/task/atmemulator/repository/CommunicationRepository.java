package com.egs.task.atmemulator.repository;

import com.egs.task.atmemulator.model.CommunicationNetwork;
import com.egs.task.atmemulator.model.Role;
import com.egs.task.atmemulator.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunicationRepository extends JpaRepository<CommunicationNetwork, Long> {

    @Override
    List<CommunicationNetwork> findAll();
}
