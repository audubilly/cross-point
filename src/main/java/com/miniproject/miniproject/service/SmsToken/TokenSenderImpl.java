package com.miniproject.miniproject.service.SmsToken;

import com.miniproject.miniproject.data.model.TwilioConfiguration;
import com.miniproject.miniproject.data.model.User;
import com.miniproject.miniproject.web.exceptions.TokenException;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenSenderImpl implements TokenSender {

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TokenSenderImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }


    @Override
    public void sendToken(User user) throws TokenException {
        try{
            if(isPhoneNumberValid(user.getPhoneNumber())){
                PhoneNumber to = new PhoneNumber(user.getPhoneNumber());
                PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
                String verificationCode = user.getVerificationCode();
                String id = user.getId();
                String message = "Welcome to crosspoint, here is your id : " + id +
                        "and your verification code :" + verificationCode;
                MessageCreator creator = Message.creator(to,from,message);
                creator.create();
                log.info("Send code {} to {}", verificationCode, user.getPhoneNumber());
            }else {
                throw  new TokenException(
                        "Phone number [" + user.getPhoneNumber() + "] is not a valid number"
                );
            }
        }catch (ApiException apiException){
            throw new TokenException(apiException.getMessage());
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return true;
    }
}
