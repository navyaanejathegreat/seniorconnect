package com.seniorconnect.seniorconnect.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "senior_profiles")
@Data
@NoArgsConstructor
public class SeniorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String company;
    private String role;
    private String domain;
    private float cgpa;
    private String branch;
    private String skills;
    private int placementYear;

    @Column(length = 1000)
    private String bio;
}