package dev.curtis.northroad.kafka;

import dev.curtis.northroad.model.LogEntry;
import dev.curtis.northroad.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final LogService logService;

    @KafkaListener(topics = "application-logs", groupId = "logging-group")
    public void consumeLog(LogEntry logEntry) {
        try {
            logService.saveLog(logEntry);
            log.info("Successfully processed log entry: {}", logEntry.getId());
        } catch (Exception e) {
            log.error("Error processing log entry: {}", logEntry.getId(), e);
        }
    }
}