package com.sidronio.chapter03autoconfigure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(GreetingAutoConfiguration.class));

    @Test
    void shouldRegisterGreetingServiceByDefault() {
        contextRunner.run((context) -> {
            assertThat(context)
                    .hasSingleBean(GreetingService.class);
            GreetingService greetingService = context.getBean(GreetingService.class);
            assertThat(greetingService.greet("World")).isEqualTo("Hello, World!");
        });
    }

    @Test
    void shouldBackOffWhenUserProvidesCustomBean() {
        contextRunner
                .withUserConfiguration(CustomUserConfig.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(GreetingService.class);
                    GreetingService service = context.getBean(GreetingService.class);
                    assertThat(service.greet("Partner")).isEqualTo("Howdy, Partner!");
                });
    }

    @Test
    void shouldNotRegisterWhenDisabledByProperty() {
        contextRunner
                .withPropertyValues("app.greeting.enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(GreetingService.class);
                });
    }

    @Configuration(proxyBeanMethods = false)
    static class CustomUserConfig {
        @Bean
        GreetingService customGreetingService() {
            return new GreetingService("Howdy");
        }
    }
}
