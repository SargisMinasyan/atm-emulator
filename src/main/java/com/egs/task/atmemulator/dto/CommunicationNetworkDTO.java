package com.egs.task.atmemulator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunicationNetworkDTO {
    String contactAsWithNumber;
    String webSite;
    String emailAddress;

    public CommunicationNetworkDTO(String contactAsWithNumber, String webSite, String emailAddress) {
        this.contactAsWithNumber = contactAsWithNumber;
        this.webSite = webSite;
        this.emailAddress = emailAddress;
    }

    public CommunicationNetworkDTO() {
    }
}
