package com.hackathon2020.client;

import com.hackathon2020.dao.MeetingDao;
import com.hackathon2020.dao.ServiceDao;
import com.hackathon2020.dao.UserDao;
import com.hackathon2020.domain.Meeting;
import com.hackathon2020.domain.Service;
import com.hackathon2020.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/client/service")
public class ClientController {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private MeetingDao meetingDao;

    @Autowired
    private UserDao userDao;

    @GetMapping
    public boolean createNowMeeting(String login, String serviceId) {
        User user = userDao.getByLogin(login);
        Service service = serviceDao.getById(serviceId);
        Meeting meeting = new Meeting(UUID.randomUUID().toString(), null,
                user, null, service, LocalDateTime.now());
        meetingDao.save(meeting);
        return true;
    }

    @GetMapping
    public boolean createScheduledMeeting(String login, String serviceId, LocalDateTime dateTime) {
        User user = userDao.getByLogin(login);
        Service service = serviceDao.getById(serviceId);
        Meeting meeting = new Meeting(UUID.randomUUID().toString(), null,
                user, null, service, dateTime);
        meetingDao.save(meeting);
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
