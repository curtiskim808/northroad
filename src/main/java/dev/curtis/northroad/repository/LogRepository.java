package dev.curtis.northroad.repository;

import dev.curtis.northroad.model.LogEntry;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LogRepository extends CassandraRepository<LogEntry, UUID> {
    List<LogEntry> findByServiceId(UUID serviceId);

    List<LogEntry> findByServiceName(String serviceName);

    @Query("SELECT * FROM logs WHERE timestamp > ?0 AND timestamp < ?1 ALLOW FILTERING")
    List<LogEntry> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    List<LogEntry> findByEventType(String eventType);
}
