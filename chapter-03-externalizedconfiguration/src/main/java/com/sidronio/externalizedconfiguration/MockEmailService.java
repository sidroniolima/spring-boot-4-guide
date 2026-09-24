package com.sidronio.externalizedconfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@Conditional(OnLinuxCondition.class)
public class MockEmailService implements EmailService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void send(String to, String body) {
        logger.info("Sending mock email to {} with body {}", to, body);
    }
}
