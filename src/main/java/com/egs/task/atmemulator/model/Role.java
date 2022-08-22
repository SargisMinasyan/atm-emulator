package com.egs.task.atmemulator.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@Getter
public class Role extends BaseEntity implements Serializable {
    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    protected Role() {
    }

    protected Role(UserRole roleName) {
        this.roleName = roleName;
    }

    public static Role of() {
        return new Role(UserRole.ROLE_USER);
    }
}
