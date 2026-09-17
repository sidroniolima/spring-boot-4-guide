package com.sidronio.chapter03consumer;

import com.sidronio.chapter03autoconfigure.GreetingService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter03ConsumerApplication implements CommandLineRunner {

    @Autowired
    private GreetingService greetingService;

    public static void main(String[] args) {
        SpringApplication.run(Chapter03ConsumerApplication.class, args);
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        System.out.println(greetingService.greet("Sidronio"));
    }
}
