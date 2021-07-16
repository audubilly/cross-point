package com.miniproject.miniproject.service.MailGun;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.miniproject.miniproject.data.model.MailGunConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailSenderImpl implements MailSender{

    private final MailGunConfiguration mailGunConfiguration;

    private static final String YOUR_DOMAIN_NAME = "sandbox7427c12b634541d1a246194d8d53eda4.mailgun.org"; //TODO put your domain name here

    @Autowired
    public MailSenderImpl(MailGunConfiguration mailGunConfiguration) {
        this.mailGunConfiguration = mailGunConfiguration;
    }



    @Override
    public JsonNode sendSimpleMessage() throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
                .basicAuth("api", mailGunConfiguration.getApiKey())
                .field("from", "audubilly89@gmail.com")
                .field("to", mailGunConfiguration.getEmailAddress())//TODO put your emails here
                .field("subject", "Hello From Crosspoint")
                .field("text", "Testing crosspoint email sender")
                .asJson();

        return request.getBody();


    }


    }

