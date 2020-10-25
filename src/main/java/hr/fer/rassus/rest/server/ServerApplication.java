package hr.fer.rassus.rest.server;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
	public static Logger logger = LoggerFactory.getLogger(ServerApplication.class);

	public static void main(String[] args) {
		logger.info("Server is starting");
		SpringApplication.run(ServerApplication.class, args);
	}

}
