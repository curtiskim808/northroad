package dev.curtis.northroad.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraOperations;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CassandraConfigTest {
    @Autowired
    private CassandraOperations cassandraOperations;

    @Test
    void testCassandraOperations() {
        assertNotNull(cassandraOperations);
    }
}