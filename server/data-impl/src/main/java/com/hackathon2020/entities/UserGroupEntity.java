package com.hackathon2020.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "USER_GROUP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupEntity implements BaseEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private GroupEntity group;

    @Override
    public List<String> getBaseFields() {
        return Arrays.asList("id");
    }

    @Override
    public List<String> getJoinFields() {
        return Arrays.asList("user", "group");
    }
}
