package com.hackathon2020.dao;

import com.hackathon2020.domain.Service;
import com.hackathon2020.entities.ServiceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceDao extends CommonDao<Service, ServiceEntity> {
    @Override
    public Class<ServiceEntity> getEntityClass() {
        return ServiceEntity.class;
    }

    @Override
    public void remove(String id, List<String> joinIds) {

    }

    @Override
    public void update(Service domain, List<String> removeIds) {

    }
}
