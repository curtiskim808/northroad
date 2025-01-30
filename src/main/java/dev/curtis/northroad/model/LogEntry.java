package dev.curtis.northroad.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Table("logs")
public class LogEntry {
    @PrimaryKey
    private UUID id;
    private UUID serviceId;
    private String serviceName;
    private OffsetDateTime timestamp;
    private String logLevel;
    private String eventType;
    private String message;
    private Map<String, String> context;

}
