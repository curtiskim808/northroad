package dev.curtis.northroad.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LogEntryTest {

    @Test
    void shouldCreateLogEntry() {
        UUID id = UUID.randomUUID();
        UUID serviceId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        LogEntry logEntry = LogEntry.builder()
                .id(id)
                .serviceId(serviceId)
                .serviceName("test-service")
                .timestamp(now)
                .logLevel("INFO")
                .eventType("TEST")
                .message("Test message")
                .context(new HashMap<>())
                .build();

        assertThat(logEntry.getId()).isEqualTo(id);
        assertThat(logEntry.getServiceId()).isEqualTo(serviceId);
        assertThat(logEntry.getServiceName()).isEqualTo("test-service");
        assertThat(logEntry.getTimestamp()).isEqualTo(now);
    }
}