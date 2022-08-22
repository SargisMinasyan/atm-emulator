package com.egs.task.atmemulator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private UUID uuid;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public BaseEntity() {
        this.uuid = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}