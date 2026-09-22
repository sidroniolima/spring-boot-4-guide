package com.sidronio.externalizedconfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "email.features", name = "custom", havingValue = "true")
public class CustomEmailService implements EmailService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void send(String to, String body) {
        logger.info("Sending custom email to {} with body {}", to, body);
    }
}
