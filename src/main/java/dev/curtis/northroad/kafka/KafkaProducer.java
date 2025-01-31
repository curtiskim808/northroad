package dev.curtis.northroad.kafka;
import dev.curtis.northroad.model.LogEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, LogEntry> kafkaTemplate;

    public void sendLog(LogEntry logEntry) {
        kafkaTemplate.send("application-logs",
                logEntry.getServiceId().toString(),
                logEntry);
    }
}