package com.egs.task.atmemulator.model;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
public class Role extends BaseEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    protected Role() {}

    protected Role(UserRole roleName) {
        this.roleName = roleName;
    }

    public static Role of(){
        return new Role(UserRole.ROLE_USER);
    }
}
