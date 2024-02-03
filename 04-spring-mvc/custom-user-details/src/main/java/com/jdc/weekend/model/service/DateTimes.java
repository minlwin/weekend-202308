package com.jdc.weekend.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DateTimes {
	
	private DateTimeFormatter dateTime;
	
	@Value("${spring.mvc.format.date-time}")
	private String dateTimeFormat;
	
	@PostConstruct
	private void init() {
		this.dateTime = DateTimeFormatter.ofPattern(dateTimeFormat);
	}
	
	public String format(LocalDateTime date) {
		if(null != date) {
			return date.format(dateTime);
		}
		
		return null;
	}
}
