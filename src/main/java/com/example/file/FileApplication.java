package com.example.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.config.QuartzConfig;

@SpringBootApplication
@Import({ QuartzConfig.class })
public class FileApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileApplication.class, args);
	}
}
