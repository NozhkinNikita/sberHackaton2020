package com.hackathon2020.entities;

import java.util.List;

public interface BaseEntity {

    String getId();

    List<String> getBaseFields();

    List<String> getJoinFields();
}
