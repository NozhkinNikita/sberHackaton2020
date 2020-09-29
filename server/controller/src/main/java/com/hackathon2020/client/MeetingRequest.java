package com.hackathon2020.client;

public class MeetingRequest {

    private String login;
    private String serviceId;

    public MeetingRequest(String login, String serviceId) {
        this.login = login;
        this.serviceId = serviceId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}