package com.mobilnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MobilnetApplication {

	public static void main(String[] args) {
		com.mobilnet.config.DotenvLoader.loadEnv();
		SpringApplication.run(MobilnetApplication.class, args);
	}
}
