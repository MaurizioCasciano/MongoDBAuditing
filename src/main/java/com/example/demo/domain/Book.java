package com.example.demo.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@SuperBuilder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Book extends AuditingDocument {
    @Id
    private String id;

    private String name;
}
