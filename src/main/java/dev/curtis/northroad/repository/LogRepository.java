package dev.curtis.northroad.repository;

import dev.curtis.northroad.model.LogEntry;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LogRepository extends CassandraRepository<LogEntry, UUID> {
    List<LogEntry> findByServiceId(UUID serviceId);

    List<LogEntry> findByServiceName(String serviceName);

    @Query("SELECT * FROM logs WHERE timestamp >= :start AND timestamp <= :end ALLOW FILTERING")
    List<LogEntry> findByTimestampBetween(OffsetDateTime start, OffsetDateTime end);

    List<LogEntry> findByEventType(String eventType);
}
