package com.egs.task.atmemulator;

import com.egs.task.atmemulator.dto.CommunicationNetworkDTO;
import com.egs.task.atmemulator.dto.MessageDTO;
import com.egs.task.atmemulator.model.ATMUser;
import com.egs.task.atmemulator.model.CommunicationNetwork;
import com.egs.task.atmemulator.model.Role;
import com.egs.task.atmemulator.repository.ATMUserRepository;
import com.egs.task.atmemulator.repository.CommunicationRepository;
import com.egs.task.atmemulator.repository.MessagesRepository;
import com.egs.task.atmemulator.repository.UserRoleRepository;
import com.egs.task.atmemulator.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

class BaseControllerTest extends BaseTestConfig {

  private final String LOCAL_HOST = "http://localhost:";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  private ATMUserRepository userRepository;
  @Autowired
  private MessagesRepository messagesRepository;
  @Autowired
  private UserRoleRepository roleRepository;
  @Autowired
  private UserServiceImpl userService;
  @Autowired
  private CommunicationRepository communicationRepository;
  private ATMUser atmUser;
  private Role role;
  private CommunicationNetwork communicationNetwork;
  @BeforeEach
  void cleanUp() {
    messagesRepository.deleteAll();
    communicationRepository.deleteAll();
    userRepository.deleteAll();
    roleRepository.deleteAll();

  }


  @Test
  void balance() throws Exception {
    role = Role.of();
    roleRepository.save(role);
    String encode = passwordEncoder.encode("1568");
    atmUser = com.egs.task.atmemulator.model.ATMUser.of("sargisss.minasyan@mail.ru",encode,"SEVAKss"
            ,"15648911","1561", LocalDateTime.now(),role, 10000L);
    userRepository.save(atmUser);
    userService.addMessages("15648911","SARGISTEST","SARGISTEST");
    communicationNetwork = communicationRepository.save(new CommunicationNetwork("+37455055520", "test", "testing"));
    String response = this.restTemplate.withBasicAuth("15648911","1568").getForObject(LOCAL_HOST + port + "/user/get/user/balance",
            String.class, atmUser.getId());
    Assertions.assertEquals(Long.parseLong(response), atmUser.getBalance());

  }
  @Test
  void messages() throws Exception {
    role = Role.of();
    roleRepository.save(role);
    String encode = passwordEncoder.encode("1568");
    atmUser = com.egs.task.atmemulator.model.ATMUser.of("sargisss.minasyan@mail.ru",encode,"SEVAKss"
            ,"15648911","1561", LocalDateTime.now(),role, 10000L);
    userRepository.save(atmUser);
    userService.addMessages("15648911","SARGISTEST","SARGISTEST");
    communicationNetwork = communicationRepository.save(new CommunicationNetwork("+37455055520", "test", "testing"));
    List<MessageDTO> response = this.restTemplate.withBasicAuth("15648911","1568").getForObject(LOCAL_HOST + port + "/user/get/user/messages",
            ArrayList.class, atmUser.getId());
    LinkedHashMap linkedHashMap = new LinkedHashMap<>((Map) response.get(0));
    Assertions.assertEquals(linkedHashMap.get("messages"), "SARGISTEST");

  }
  @Test
  void cashOut() throws Exception {
    role = Role.of();
    roleRepository.save(role);
    String encode = passwordEncoder.encode("1568");
    atmUser = com.egs.task.atmemulator.model.ATMUser.of("sargisss.minasyan@mail.ru",encode,"SEVAKss"
            ,"15648911","1561", LocalDateTime.now(),role, 10000L);
    userRepository.save(atmUser);
    userService.addMessages("15648911","SARGISTEST","SARGISTEST");
    communicationNetwork = communicationRepository.save(new CommunicationNetwork("+37455055520", "test", "testing"));
    Map<String,Long> map = new HashMap<>();
    Long response = this.restTemplate.withBasicAuth("15648911","1568").postForObject(LOCAL_HOST + port + "/user/cash/out?cashOut=1234",map,Long.class);
    Assertions.assertEquals(response, Long.parseLong(userRepository.findBalanceByEmail(atmUser.getEmail()).get()));

  }
  @Test
  void fillBalance() throws Exception {
    role = Role.of();
    roleRepository.save(role);
    String encode = passwordEncoder.encode("1568");
    atmUser = com.egs.task.atmemulator.model.ATMUser.of("sargisss.minasyan@mail.ru",encode,"SEVAKss"
            ,"15648911","1561", LocalDateTime.now(),role, 10000L);
    userRepository.save(atmUser);
    userService.addMessages("15648911","SARGISTEST","SARGISTEST");
    communicationNetwork = communicationRepository.save(new CommunicationNetwork("+37455055520", "test", "testing"));
    Map<String,Long> map = new HashMap<>();
    Long response = this.restTemplate.withBasicAuth("15648911","1568").postForObject(LOCAL_HOST + port + "/user/fill/balance?incomingCash=1234",map,Long.class);
    Assertions.assertEquals(response,Long.parseLong( userRepository.findBalanceByEmail(atmUser.getEmail()).get()));

  }
  @Test
  void communicationNetwork() throws Exception {
    role = Role.of();
    roleRepository.save(role);
    String encode = passwordEncoder.encode("1568");
    atmUser = com.egs.task.atmemulator.model.ATMUser.of("sargisss.minasyan@mail.ru",encode,"SEVAKss"
            ,"15648911","1561", LocalDateTime.now(),role, 10000L);
    userRepository.save(atmUser);
    userService.addMessages("15648911","SARGISTEST","SARGISTEST");
    communicationNetwork = communicationRepository.save(new CommunicationNetwork("+37455055520", "test", "testing"));
    List<CommunicationNetworkDTO> response = (List<CommunicationNetworkDTO>) this.restTemplate.getForObject(LOCAL_HOST + port + "/base_information/communication_network",new ArrayList<CommunicationNetworkDTO>().getClass());
    LinkedHashMap linkedHashMap = new LinkedHashMap<>((Map) response.get(0));
    Assertions.assertEquals(linkedHashMap.get("contactAsWithNumber"),communicationNetwork.getContactAsWithNumber());

  }
  @Test
  void addMessageByCardNumber() throws Exception {
    role = Role.of();
    roleRepository.save(role);
    String encode = passwordEncoder.encode("1568");
    atmUser = com.egs.task.atmemulator.model.ATMUser.of("sargisss.minasyan@mail.ru",encode,"SEVAKss"
            ,"15648911","1561", LocalDateTime.now(),role, 10000L);
    userRepository.save(atmUser);
    userService.addMessages("15648911","SARGISTEST","SARGISTEST");
    communicationNetwork = communicationRepository.save(new CommunicationNetwork("+37455055520", "test", "testing"));
    Map<String,Long> map = new HashMap<>();
    MessageDTO response = this.restTemplate.postForObject(LOCAL_HOST + port +
            "/base_information/add/new/message?userCardNumber=15648911&message=asdasd&title=asdfadsf",map,MessageDTO.class);

    Assertions.assertEquals(response.getMessages(),"asdasd");

  }


}
