package com.sidronio.chapter03consumer;

import com.sidronio.chapter03autoconfigure.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomGreeting {

    @Bean
    public GreetingService greetingService() {
        return new GreetingService("Uaaalaaa");
    }
}
