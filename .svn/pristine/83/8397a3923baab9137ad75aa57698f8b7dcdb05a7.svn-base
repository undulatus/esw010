package com.pointwest.workforce.planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:db-values.properties")
@SpringBootApplication
public class WorkforcePlannerApplication extends SpringBootServletInitializer {
	
	/**
     * Added for parsing
     * a JacksonJsonParser() implementation
     * @return - parser bean
     */
    @Bean
    public JsonParser jsonParser() {
    	JsonParser parser = new JacksonJsonParser();
    	return parser;
    }

	public static void main(String[] args) {
		SpringApplication.run(WorkforcePlannerApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WorkforcePlannerApplication.class);
    }
}
