package com.hackathon2020.client;

public class MeetingResponse {

    private String url;

    public MeetingResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}