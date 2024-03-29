package com.fiap.hackathon.gateway.misc.common;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableJPA {

    @Column(nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    protected LocalDateTime modifiedAt;

    @Column
    protected LocalDateTime deletedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
