package com.example.dental_db.dentalrecords;

import com.example.dental_db.dentalrecords.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class DentalRecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentalRecordsApplication.class, args);
	}

}
