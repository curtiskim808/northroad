package dev.curtis.northroad.service;

import dev.curtis.northroad.model.LogEntry;
import dev.curtis.northroad.repository.LogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogServiceImplTest {

    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private LogServiceImpl logService;

    @Test
    void shouldSaveLog() {
        LogEntry logEntry = new LogEntry();
        when(logRepository.save(any(LogEntry.class))).thenReturn(logEntry);

        logService.saveLog(logEntry);

        verify(logRepository).save(logEntry);
    }
}