package dev.curtis.northroad.controller;

import dev.curtis.northroad.config.CassandraConfig;
import dev.curtis.northroad.config.KafkaConfig;
import dev.curtis.northroad.model.LogEntry;
import dev.curtis.northroad.service.LogService;
import lombok.With;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LogControllerTest {
    @Autowired
    private CassandraTemplate cassandraTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LogService logService;

    @BeforeEach
    void setUp() {
        cassandraTemplate.getCqlOperations().execute("CREATE INDEX IF NOT EXISTS ON logs (timestamp)");
    }

    @Test
    @WithMockUser
    void shouldGetLogs() throws Exception {
        when(logService.findAll()).thenReturn(Arrays.asList(new LogEntry(), new LogEntry()));
        mockMvc.perform(get("/api/v1/logs"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser
    void shouldGetLogsByServiceId() throws Exception {
        UUID serviceId = UUID.randomUUID();
        when(logService.findByServiceId(any(UUID.class)))
                .thenReturn(Arrays.asList(new LogEntry()));

        mockMvc.perform(get("/api/v1/logs/service/" + serviceId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetLogsByServiceName() throws Exception {
        String serviceName = "service";
        when(logService.findByServiceName(any(String.class)))
                .thenReturn(Arrays.asList(new LogEntry()));

        mockMvc.perform(get("/api/v1/logs/service/name/" + serviceName))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetLogsByTimeRange() throws Exception {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        when(logService.findByTimeRange(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(new LogEntry()));

        mockMvc.perform(get("/api/v1/logs/timerange")
                        .param("start", start.toString())
                        .param("end", end.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetLogsByEventType() throws Exception {
        String eventType = "event";
        when(logService.findByEventType(any(String.class)))
                .thenReturn(Arrays.asList(new LogEntry()));

        mockMvc.perform(get("/api/v1/logs/event/" + eventType))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldCleanupOldLogs() throws Exception {
        LocalDateTime before = LocalDateTime.now();
        doNothing().when(logService).deleteOldLogs(any(LocalDateTime.class));
        mockMvc.perform(delete("/api/v1/logs/cleanup")
                        .param("before", before.toString())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}