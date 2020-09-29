package com.hackathon2020.dao;

import com.hackathon2020.dao.filters.SearchCondition;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.domain.Meeting;
import com.hackathon2020.entities.MeetingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetingDao extends CommonDao<Meeting, MeetingEntity> {

    @Autowired
    private UserGroupDao userGroupDao;

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

    public List<Meeting> getByUserId(String userId) {
        SimpleCondition condition = new SimpleCondition
                .Builder()
                .setSearchField("userId")
                .setSearchCondition(SearchCondition.EQUALS)
                .setSearchValue(userId)
                .build();
        return getByCondition(condition);
    }

//    public List<Meeting> getActiveMeetingsByEmployeeLogin(String login) {
//        EntityManager em = getEntityManager();
//        try {
//            ComplexCondition condition = new ComplexCondition.Builder()
//
//                    .build()
//        } finally {
//            em.close();
//        }
//
//    }

}
