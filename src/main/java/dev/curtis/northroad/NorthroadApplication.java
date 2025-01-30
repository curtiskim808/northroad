package dev.curtis.northroad;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		CassandraDataAutoConfiguration.class,
		CassandraAutoConfiguration.class
})
public class NorthroadApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthroadApplication.class, args);
	}

}
