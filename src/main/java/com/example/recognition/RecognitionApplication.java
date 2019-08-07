package com.example.recognition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.example.**.mapper")
@SpringBootApplication/*(exclude = DataSourceAutoConfiguration.class)*/
public class RecognitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecognitionApplication.class,args);
	}
}
