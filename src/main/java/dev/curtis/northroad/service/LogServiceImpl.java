package dev.curtis.northroad.service;

import dev.curtis.northroad.model.LogEntry;
import dev.curtis.northroad.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public List<LogEntry> findAll() {
        return logRepository.findAll();
    }

    @Override
    public LogEntry saveLog(LogEntry logEntry) {
        if (logEntry.getId() == null) {
            logEntry.setId(UUID.randomUUID());
        }
        if (logEntry.getTimestamp() == null) {
            logEntry.setTimestamp(LocalDateTime.now());
        }
        return logRepository.save(logEntry);
    }

    @Override
    public List<LogEntry> findByServiceId(UUID serviceId) {
        return logRepository.findByServiceId(serviceId);
    }

    @Override
    public List<LogEntry> findByServiceName(String serviceName) {
        return logRepository.findByServiceName(serviceName);
    }

    @Override
    public List<LogEntry> findByTimeRange(LocalDateTime start, LocalDateTime end) {
        return logRepository.findByTimestampBetween(start, end);
    }

    @Override
    public List<LogEntry> findByEventType(String eventType) {
        return logRepository.findByEventType(eventType);
    }

    @Override
    public void deleteOldLogs(LocalDateTime before) {
        List<LogEntry> oldLogs = logRepository.findByTimestampBetween(
                before.minusYears(10), before);
        logRepository.deleteAll(oldLogs);
    }
}