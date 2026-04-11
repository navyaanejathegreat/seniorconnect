package com.seniorconnect.seniorconnect.model;

import lombok.Data;

@Data
public class MentorshipRequestDTO {
    private Long juniorId;
    private Long seniorId;
    private String message;
}