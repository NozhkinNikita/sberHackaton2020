package com.hackathon2020.client;

import com.hackathon2020.dao.GroupDao;
import com.hackathon2020.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/client/groups")
public class GroupController {

    @Autowired
    private GroupDao groupDao;

    @GetMapping
    public List<Group> getGroups() {
        return groupDao.getAll();
    }

    @GetMapping(value = "/{id}")
    public Group getGroupById(@PathVariable String id) {
        return groupDao.getById(id);
    }
}
