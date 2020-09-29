package com.hackathon2020.dao;

import com.hackathon2020.dao.filters.SearchCondition;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.domain.Meeting;
import com.hackathon2020.entities.MeetingEntity;
import com.hackathon2020.entities.MeetingEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetingDao extends CommonDao<Meeting, MeetingEntity> {
    @Override
    public Class<MeetingEntity> getEntityClass() {
        return MeetingEntity.class;
    }

    @Override
    public void remove(String id, List<String> joinIds) {

    }

    @Override
    public void update(Meeting domain, List<String> removeIds) {

    }

    public List<Meeting> getAll() {
        SimpleCondition condition = new SimpleCondition
                .Builder()
                .setSearchField("id")
                .setSearchCondition(SearchCondition.NOT_NULL)
                .build();
        return getByCondition(condition);
    }

    public List<Meeting> getByGroupId(String groupId) {
        SimpleCondition condition = new SimpleCondition
                .Builder()
                .setSearchField("groupId")
                .setSearchCondition(SearchCondition.EQUALS)
                .setSearchValue(groupId)
                .build();
        return getByCondition(condition);
    }

}
