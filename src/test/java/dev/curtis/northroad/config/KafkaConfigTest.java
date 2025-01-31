package dev.curtis.northroad.config;

import dev.curtis.northroad.model.LogEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class KafkaConfigTest {
    @Autowired
    private NewTopic logTopic;

    @Autowired
    private KafkaTemplate<String, LogEntry> kafkaTemplate;

    @Test
    void testLogTopic() {
        assertThat(logTopic).isNotNull();
    }

    @Test
    void testKafkaTemplate() {
        assertThat(kafkaTemplate).isNotNull();
    }
}