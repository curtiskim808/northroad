package dev.curtis.northroad.service;

import dev.curtis.northroad.model.LogEntry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface LogService {
    LogEntry saveLog(LogEntry logEntry);
    List<LogEntry> findByServiceId(UUID serviceId);
    List<LogEntry> findByServiceName(String serviceName);
    List<LogEntry> findByTimeRange(LocalDateTime start, LocalDateTime end);
    List<LogEntry> findByEventType(String eventType);
    void deleteOldLogs(LocalDateTime before);
}
