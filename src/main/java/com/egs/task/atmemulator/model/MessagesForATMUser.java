package com.egs.task.atmemulator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class MessagesForATMUser extends BaseEntity {

    private String messages;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private ATMUser user;

    public MessagesForATMUser(String messages,ATMUser user) {
        this.user = user;
        this.messages = messages;
    }

    public MessagesForATMUser() {
    }

}
