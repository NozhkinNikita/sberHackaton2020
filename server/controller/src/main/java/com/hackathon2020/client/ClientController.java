package com.hackathon2020.client;

import com.hackathon2020.dao.GroupDao;
import com.hackathon2020.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/client/service")
public class ClientController {

    @Autowired
    private GroupDao groupDao;

    @GetMapping
    public boolean createNowMeeting(String userId, String serviceId) {

        return true;
    }

    @GetMapping
    public boolean createScheduledMeeting(String login, String serviceId, LocalDateTime dateTime) {
        return true;
    }

    @GetMapping(value = "/{id}")
    @Transactional(timeout = 120)
    public Group getMyScheduledMeetings(String login) {

    }
}
