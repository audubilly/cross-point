package com.miniproject.miniproject.service.SmsToken;

import com.miniproject.miniproject.data.model.User;
import com.miniproject.miniproject.web.exceptions.TokenException;
import org.springframework.stereotype.Service;

@Service
public interface TokenSender {
    void sendToken(User user) throws TokenException;
}
