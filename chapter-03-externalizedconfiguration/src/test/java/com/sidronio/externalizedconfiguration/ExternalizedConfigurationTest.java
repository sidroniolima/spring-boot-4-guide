package com.sidronio.externalizedconfiguration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class ExternalizedConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(
                    CustomEmailService.class,
                    MockEmailService.class,
                    SendGridEmailService.class);

    private String originalOs;

    @BeforeEach
    void saveOriginal() {
        originalOs = System.getProperty("os.name");
    }

    @AfterEach
    void restoreOriginal() {
        if (originalOs != null) {
            System.setProperty("os.name", originalOs);
        }
    }

    @Test
    void shouldLoadCustomEmailService() {
        System.setProperty("os.name", "Windows 11");

        contextRunner.withPropertyValues("email.features.custom=true", "email.features.enabled=true")
                .run(context -> {
                    assertThat(context)
                            .hasSingleBean(CustomEmailService.class);
                    assertThat(context)
                            .doesNotHaveBean(MockEmailService.class);
                    assertThat(context)
                            .doesNotHaveBean(SendGridEmailService.class);
                });
    }

    @Test
    void shouldLoadMockEmailService() {

        contextRunner.withPropertyValues("email.features.custom=false", "email.features.enabled=true")
                .run(context -> {
                    assertThat(context)
                            .doesNotHaveBean(CustomEmailService.class);
                    assertThat(context)
                            .hasSingleBean(MockEmailService.class);
                    assertThat(context)
                            .doesNotHaveBean(SendGridEmailService.class);
                });
    }

    @Test
    void shouldNotLoadPaymentProperties() {
        contextRunner.withPropertyValues("spring.profiles.active=prod", "email.features.enabled=false")
                .run(context -> {
                    assertThat(context)
                            .doesNotHaveBean(CustomEmailService.class);
                    assertThat(context)
                            .hasSingleBean(MockEmailService.class);
                    assertThat(context)
                            .hasSingleBean(SendGridEmailService.class);
                });
    }
}
