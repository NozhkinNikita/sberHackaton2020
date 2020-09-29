package com.hackathon2020.dao;

import com.hackathon2020.domain.UserGroup;
import com.hackathon2020.entities.UserGroupEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGroupDao extends CommonDao<UserGroup, UserGroupEntity> {
    @Override
    public Class<UserGroupEntity> getEntityClass() {
        return UserGroupEntity.class;
    }

    @Override
    public void remove(String id, List<String> joinIds) {

    }

    @Override
    public void update(UserGroup domain, List<String> removeIds) {

    }
}
