package com.seniorconnect.seniorconnect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class MentorshipRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "junior_id")
    private User junior;

    @ManyToOne
    @JoinColumn(name = "senior_id")
    private User senior;

    private String message;

    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();
}