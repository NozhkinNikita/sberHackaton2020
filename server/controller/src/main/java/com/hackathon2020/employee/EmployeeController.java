package com.hackathon2020.employee;

import com.hackathon2020.dao.MeetingDao;
import com.hackathon2020.dao.ServiceDao;
import com.hackathon2020.dao.UserDao;
import com.hackathon2020.domain.Meeting;
import com.hackathon2020.domain.Service;
import com.hackathon2020.domain.User;
import com.hackathon2020.security.CredentialUtils;
import com.hackathon2020.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping(value = "/employee/services/")
public class EmployeeController {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private MeetingDao meetingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CredentialUtils credentialUtils;

    @PostMapping(value = "/{serviceId}/join")
    public ResponseEntity<String> joinMeeting(@PathVariable String meetingId) {
        User user = credentialUtils.getUserInfo();
        Meeting meeting = meetingDao.getById(meetingId);
        meeting.setEmployee(user);
        meetingDao.save(meeting);
        return ResponseEntity.ok("321");
    }

    @GetMapping(value = "/{serviceId}/join")
    public ResponseEntity<String> joinScheduledMeeting(@PathVariable String serviceId, LocalDateTime dateTime) {
        User user = credentialUtils.getUserInfo();
        Service service = serviceDao.getById(serviceId);
        Meeting meeting = new Meeting(UUID.randomUUID().toString(), null,
                user, null, service, LocalDateTime.now());
        meetingDao.save(meeting);
        return ResponseEntity.ok("123");
    }

    @GetMapping(value= "/getUserScheduledMeetings")
    @Transactional(timeout = 120)
    public List<Meeting> getUserScheduledMeetings(String login) {
        String userId = userDao.getByLogin(login).getId();
        List<Meeting> meetings = meetingDao.getByUserId(userId);
        return meetings;
    }
}