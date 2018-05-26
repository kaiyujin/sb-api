package com.kaiyujin.sb.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @CreatedBy
    private String createdBy;

    @CreationTimestamp
    private Timestamp createdAt;

    @LastModifiedBy
    private String updatedBy;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
