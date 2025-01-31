package dev.curtis.northroad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("logs")
public class LogEntry {
    @PrimaryKey
    private UUID id;
    @Column("service_id")
    private UUID serviceId;
    @Column("service_name")
    private String serviceName;
    private LocalDateTime timestamp;
    @Column("log_level")
    private String logLevel;
    @Column("event_type")
    private String eventType;
    private String message;
    private Map<String, String> context;

}
