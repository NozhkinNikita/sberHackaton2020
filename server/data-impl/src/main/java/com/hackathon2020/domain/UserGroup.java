package com.hackathon2020.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {
    private String id;

    private User user;

    private Group group;
}
