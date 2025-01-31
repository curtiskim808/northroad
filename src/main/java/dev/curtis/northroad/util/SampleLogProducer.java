package dev.curtis.northroad.util;

import dev.curtis.northroad.kafka.KafkaProducer;
import dev.curtis.northroad.model.LogEntry;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SampleLogProducer {

    private final KafkaProducer kafkaProducer;

    @Scheduled(fixedRate = 30000) // Produces a log every 5 seconds
    public void generateSampleLog() {
        LogEntry logEntry = LogEntry.builder()
                .id(UUID.randomUUID())
                .serviceId(UUID.randomUUID())
                .serviceName("sample-service")
                .timestamp(LocalDateTime.now())
                .logLevel("INFO")
                .eventType("SYSTEM_TRIGGER")
                .message("Sample log message")
                .context(new HashMap<>())
                .build();

        kafkaProducer.sendLog(logEntry);
        System.out.println("===============Produced log: " + logEntry + "==================");
    }
}