package com.kaiyujin.sb.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class BaseEntity {
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @LastModifiedBy
    private String updatedBy;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
