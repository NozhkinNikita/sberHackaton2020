package com.hackathon2020.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, List<WebSocketSession>> activeSessionsByGroup = new ConcurrentHashMap<>();
    private List<WebSocketSession> activeSessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        Message incomingMessage = parseMessage(message);
        if (incomingMessage != null) {
            switch (incomingMessage.getCommand()) {
                case SUBSCRIBE: {
                    log.info("SUBSCRIBED");
//                    activeSessions.putAll(incomingMessage.get);
                    Message msg = new Message();
                    msg.setMessage("Sucess");
                    sendMessage(session, msg);
                    break;
                }
                case SEND: {

                    break;
                }
                case UNSUBSCRIBE: {

                    break;
                }
                default: {
                    throw new IllegalArgumentException(String.format("Command %s is not supported", incomingMessage.getCommand()));
                }
            }
        }

    }

    private Message parseMessage(TextMessage message) {
        try {
            return objectMapper.readValue(message.getPayload(), Message.class);
        } catch (IOException e) {
            log.error("Cannot parse web socket message= {}. Exception: {}", message.getPayloadLength(), e);
        }
        return null;
    }

    private void sendMessage(WebSocketSession webSocketSession, Message message) {
        try {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(
                        Objects.requireNonNull(serializeMessage(message))));
            }
        } catch (IOException e) {
            log.error("Error while sending message {}. Exception: {}", message, e);
        }
    }

    private String serializeMessage(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (IOException e) {
            log.error("Cannot send message to application: {}. Exception: {}", message, e);
        }
        return null;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
        activeSessions.add(session);
    }
}
