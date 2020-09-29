package com.hackathon2020.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Meeting {
    private String id;
    private String clientId;
    private String employeeId;
    private LocalDateTime dateTime;
    private String url;
}
