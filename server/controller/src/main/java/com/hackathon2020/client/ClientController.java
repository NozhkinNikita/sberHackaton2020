package com.hackathon2020.client;

import com.hackathon2020.dao.GroupDao;
import com.hackathon2020.dao.MeetingDao;
import com.hackathon2020.dao.UserDao;
import com.hackathon2020.domain.Group;
import com.hackathon2020.domain.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/client/service")
public class ClientController {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private MeetingDao meetingDao;

    @Autowired
    private UserDao userDao;

    @GetMapping
    public boolean createNowMeeting(String userId, String serviceId) {
        return true;
    }

    @GetMapping
    public boolean createScheduledMeeting(String login, String serviceId, LocalDateTime dateTime) {
        return true;
    }

    @GetMapping
    @Transactional(timeout = 120)
    public List<Meeting> getMyScheduledMeetings(String login) {
        String userId = userDao.getByLogin(login).getId();
        List<Meeting> meetings = meetingDao.getByUserId(userId);
        return meetings;
    }
}
