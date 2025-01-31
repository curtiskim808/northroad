package dev.curtis.northroad.kafka;

import dev.curtis.northroad.model.LogEntry;
import dev.curtis.northroad.service.LogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaConsumerTest {

    @Mock
    private LogService logService;

    @InjectMocks
    private KafkaConsumer kafkaConsumer;

    @Test
    void shouldConsumeAndSaveLog() {
        LogEntry logEntry = new LogEntry();
        kafkaConsumer.consumeLog(logEntry);
        verify(logService).saveLog(any(LogEntry.class));
    }
}