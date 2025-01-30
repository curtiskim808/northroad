package dev.curtis.northroad.service;

import dev.curtis.northroad.model.LogEntry;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface LogService {
    LogEntry saveLog(LogEntry logEntry);
    List<LogEntry> findByServiceId(UUID serviceId);
    List<LogEntry> findByServiceName(String serviceName);
    List<LogEntry> findByTimeRange(OffsetDateTime start, OffsetDateTime end);
    List<LogEntry> findByEventType(String eventType);
    void deleteOldLogs(OffsetDateTime before);
}
