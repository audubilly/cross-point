package com.miniproject.miniproject.service.MailGun;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public interface MailSender {
    JsonNode sendSimpleMessage() throws UnirestException;
}
