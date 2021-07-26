package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditingDocument {
    @Version
    private Long version;

    @CreatedBy
    private String creator;
    @CreatedDate
    private Instant created;

    @LastModifiedBy
    private String modifier;
    @LastModifiedDate
    private Instant modified;
}