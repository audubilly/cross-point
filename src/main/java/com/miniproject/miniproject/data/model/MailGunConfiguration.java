package com.miniproject.miniproject.data.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@ConfigurationProperties(prefix="private")
public class MailGunConfiguration {

    @Value("${private.api_key}")
    private String apiKey;
    @Value("${private.validation_key}")
    private String validationKey;
    @Value("private.Httpo_webhook_signingkey")
    private String HttpoWebhookSigningKey;
    @Value("private.emailAddress")
    private String emailAddress;


    public MailGunConfiguration(){
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getValidationKey() {
        return validationKey;
    }

    public void setValidationKey(String validationKey) {
        this.validationKey = validationKey;
    }

    public String getHttpoWebhookSigningKey() {
        return HttpoWebhookSigningKey;
    }

    public void setHttpoWebhookSigningKey(String httpoWebhookSigningKey) {
        HttpoWebhookSigningKey = httpoWebhookSigningKey;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


}
