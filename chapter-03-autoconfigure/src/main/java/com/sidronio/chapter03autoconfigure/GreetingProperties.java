package com.sidronio.chapter03autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "app.greeting")
public record GreetingProperties(@DefaultValue("Hello") String message) {
}
