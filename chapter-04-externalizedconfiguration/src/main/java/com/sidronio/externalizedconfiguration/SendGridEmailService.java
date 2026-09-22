package com.sidronio.externalizedconfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class SendGridEmailService implements EmailService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void send(String to, String body) {
        logger.info("Sending SendGrid email to {} with body {}", to, body);
    }
}
