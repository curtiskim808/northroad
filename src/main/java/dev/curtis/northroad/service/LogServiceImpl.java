package dev.curtis.northroad.service;

import dev.curtis.northroad.model.LogEntry;
import dev.curtis.northroad.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public LogEntry saveLog(LogEntry logEntry) {
        if (logEntry.getId() == null) {
            logEntry.setId(UUID.randomUUID());
        }
        if (logEntry.getTimestamp() == null) {
            logEntry.setTimestamp(OffsetDateTime.now());
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
    public List<LogEntry> findByTimeRange(OffsetDateTime start, OffsetDateTime end) {
        return logRepository.findByTimestampBetween(start, end);
    }

    @Override
    public List<LogEntry> findByEventType(String eventType) {
        return logRepository.findByEventType(eventType);
    }

    @Override
    public void deleteOldLogs(OffsetDateTime before) {
        List<LogEntry> oldLogs = logRepository.findByTimestampBetween(
                OffsetDateTime.MIN, before);
        logRepository.deleteAll(oldLogs);
    }
}