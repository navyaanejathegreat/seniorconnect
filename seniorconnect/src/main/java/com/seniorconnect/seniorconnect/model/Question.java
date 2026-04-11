package com.seniorconnect.seniorconnect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asked_by_id")
    private User askedBy;

    private String title;

    @Column(length = 2000)
    private String body;

    private LocalDateTime createdAt = LocalDateTime.now();
}