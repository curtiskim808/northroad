package dev.curtis.northroad.util;

import dev.curtis.northroad.kafka.KafkaProducer;
import dev.curtis.northroad.model.EventType;
import dev.curtis.northroad.model.LogEntry;
import dev.curtis.northroad.model.LogLevel;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SampleLogProducer {

    private final KafkaProducer kafkaProducer;

    @Scheduled(fixedRate = 5000) // Produces a log every 5 seconds
    public void generateSampleLog() {
        LogEntry logEntry = new LogEntry();
        logEntry.setServiceId(UUID.randomUUID());
        logEntry.setServiceName("sample-service");
        logEntry.setTimestamp(OffsetDateTime.now());
        logEntry.setLogLevel(LogLevel.INFO.name());
        logEntry.setEventType(EventType.SYSTEM_TRIGGER.name());
        logEntry.setMessage("Sample log message");

        HashMap<String, String> context = new HashMap<>();
        context.put("threadId", Thread.currentThread().getName());
        context.put("asyncJob", "sample-job");
        logEntry.setContext(context);

        kafkaProducer.sendLog(logEntry);
    }
}