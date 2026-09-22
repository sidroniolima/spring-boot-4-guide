package com.sidronio.externalizedconfiguration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

public interface EmailService {
    void send(String to, String body);
}

