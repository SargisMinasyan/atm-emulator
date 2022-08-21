package com.egs.task.atmemulator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class MessagesForATMUser extends BaseEntity {

    private String title;
    private String messages;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id")
    private ATMUser user;

    private MessagesForATMUser(String messages,String title,ATMUser user) {
        this.user = user;
        this.title = title;
        this.messages = messages;
    }
    public static MessagesForATMUser of(final String  messages,final String title,final ATMUser user) {
        return new MessagesForATMUser(messages,title,user);
    }

    private MessagesForATMUser() {
    }

}
