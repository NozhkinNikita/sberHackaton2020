package com.hackathon2020.converters;

import com.hackathon2020.domain.Meeting;
import com.hackathon2020.entities.MeetingEntity;
import org.springframework.stereotype.Component;

@Component
public class MeetingConverter extends Converter<Meeting, MeetingEntity> {
    @Override
    public Class<Meeting> getDomainClass() {
        return Meeting.class;
    }

    @Override
    public Class<MeetingEntity> getEntityClass() {
        return MeetingEntity.class;
    }
}
