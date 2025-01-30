package dev.curtis.northroad.controller;

import dev.curtis.northroad.model.LogEntry;
import dev.curtis.northroad.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<LogEntry>> getLogsByServiceId(
            @PathVariable UUID serviceId) {
        return ResponseEntity.ok(logService.findByServiceId(serviceId));
    }

    @GetMapping("/service/name/{serviceName}")
    public ResponseEntity<List<LogEntry>> getLogsByServiceName(
            @PathVariable String serviceName) {
        return ResponseEntity.ok(logService.findByServiceName(serviceName));
    }

    @GetMapping("/timerange")
    public ResponseEntity<List<LogEntry>> getLogsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            OffsetDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            OffsetDateTime end) {
        return ResponseEntity.ok(logService.findByTimeRange(start, end));
    }

    @GetMapping("/event/{eventType}")
    public ResponseEntity<List<LogEntry>> getLogsByEventType(
            @PathVariable String eventType) {
        return ResponseEntity.ok(logService.findByEventType(eventType));
    }

    @DeleteMapping("/cleanup")
    public ResponseEntity<Void> cleanupOldLogs(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            OffsetDateTime before) {
        logService.deleteOldLogs(before);
        return ResponseEntity.ok().build();
    }
}