package com.egs.task.atmemulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "communication")
public class CommunicationNetwork extends BaseEntity {

    @Column(name = "contact_as_with_number")
    private String contactAsWithNumber;
    @Column(name = "web_site")
    private String webSite;
    @Column(name = "email_address")
    private String emailAddress;

    public CommunicationNetwork() {

    }

    public CommunicationNetwork( String contactAsWithNumber, String webSite, String emailAddress) {
        this.contactAsWithNumber = contactAsWithNumber;
        this.webSite = webSite;
        this.emailAddress = emailAddress;
    }
}
