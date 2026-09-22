package com.sidronio.externalizedconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PaymentProperties.class)
public class ExternalizedconfigurationApplication implements CommandLineRunner {

    @Autowired
    private PaymentProperties paymentProperties;

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(ExternalizedconfigurationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Payment gateway URL: " + paymentProperties.gatewayUrl());

        emailService.send("test@example.com", "Test email");
    }
}
