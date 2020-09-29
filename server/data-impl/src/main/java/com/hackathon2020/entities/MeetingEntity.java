package com.hackathon2020.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "MEETING")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingEntity implements BaseEntity {
    @Id
    private String id;

    private String url;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class, mappedBy = "clientId")
    private UserEntity client;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class, mappedBy = "employeeId")
    private UserEntity employee;

    @Column(name="dateTime")
    private LocalDateTime dateTime;

    @Override
    public List<String> getBaseFields() {
        return Arrays.asList("id", "name");
    }

    @Override
    public List<String> getJoinFields() {
        return Arrays.asList("client", "employee");
    }
}
