package com.stormfkrs.someone_like_this;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan
@SpringBootApplication
//@EnableAutoConfiguration
public class SomeoneLikeThisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SomeoneLikeThisApplication.class, args);
	}

}
