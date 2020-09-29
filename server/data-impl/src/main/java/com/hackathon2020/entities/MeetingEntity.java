package com.hackathon2020.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Meeting")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingEntity implements BaseEntity {
    @Id
    private String id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ServiceEntity.class, mappedBy = "groupId")
    private List<ServiceEntity> services;

    @Override
    public List<String> getBaseFields() {
        return Arrays.asList("id", "name");
    }

    @Override
    public List<String> getJoinFields() {
        return Collections.singletonList("services");
    }
}
