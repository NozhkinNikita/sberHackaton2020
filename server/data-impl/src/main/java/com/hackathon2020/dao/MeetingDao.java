package com.hackathon2020.dao;

import com.hackathon2020.dao.filters.ComplexCondition;
import com.hackathon2020.dao.filters.Operation;
import com.hackathon2020.dao.filters.SearchCondition;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.domain.Meeting;
import com.hackathon2020.domain.User;
import com.hackathon2020.domain.UserGroup;
import com.hackathon2020.entities.MeetingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetingDao extends CommonDao<Meeting, MeetingEntity> {

    @Autowired
    private UserGroupDao userGroupDao;

    @Autowired
    private UserDao userDao;

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

    public List<Meeting> getActiveMeetingsByEmployeeLogin(String login) {
        User user = userDao.getByLogin(login);
        List<UserGroup> userGroups = userGroupDao.getByUserId(user.getId());





//        SimpleCondition userGroupCondition = new SimpleCondition.Builder()
//                .setSearchField("user.login")
//                .setSearchCondition(SearchCondition.EQUALS)
//                .setSearchValue(login)
//                .build();
//
//        List<UserGroup> userGroups = userGroupDao.getByCondition(userGroupCondition);
        ComplexCondition condition = new ComplexCondition.Builder()
                .setOperation(Operation.OR)
                .setConditions(
                        userGroups.stream().map(ug ->
                                new SimpleCondition.Builder()
                                        .setSearchField("service.groupId")
                                        .setSearchCondition(SearchCondition.EQUALS)
                                        .setSearchValue(ug.getGroupId())
                                        .build()
                        ).collect(Collectors.toList())
                ).build();

        return getByCondition(condition);
    }

}
