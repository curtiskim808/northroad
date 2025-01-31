package dev.curtis.northroad.kafka;

import dev.curtis.northroad.model.LogEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaProducerTest {

    @Mock
    private KafkaTemplate<String, LogEntry> kafkaTemplate;

    @InjectMocks
    private KafkaProducer kafkaProducer;

    @Test
    void shouldSendLogToKafka() {
        LogEntry logEntry = new LogEntry();
        UUID uuid = UUID.randomUUID();
        logEntry.setServiceId(uuid);

        kafkaProducer.sendLog(logEntry);
        verify(kafkaTemplate).send("application-logs", uuid.toString(), logEntry);
    }
}