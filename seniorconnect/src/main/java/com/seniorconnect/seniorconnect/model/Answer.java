package com.seniorconnect.seniorconnect.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();
}