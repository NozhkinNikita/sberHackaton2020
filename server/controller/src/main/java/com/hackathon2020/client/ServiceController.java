package com.hackathon2020.client;

import com.hackathon2020.dao.ServiceDao;
import com.hackathon2020.domain.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/client/services")
public class ServiceController {

    @Autowired
    private ServiceDao serviceDao;

    @GetMapping(value = "/")
    public List<Service> getServices() {
        return serviceDao.getAll();
    }

    @GetMapping(value = "/{id}")
    public Service getById(@PathVariable String id) {
        return serviceDao.getById(id);
    }

    @GetMapping(value = "/groups/{groupId}")
    public List<Service> getByGroupId(@PathVariable String groupId) {
        return serviceDao.getByGroupId(groupId);
    }

    @PostMapping(value = "/{id}/call")
    public ResponseEntity<?> call(@PathVariable String id) {
        return null;
    }
}
