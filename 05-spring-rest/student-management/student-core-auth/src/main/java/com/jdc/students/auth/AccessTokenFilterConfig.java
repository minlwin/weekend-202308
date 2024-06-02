package com.jdc.students.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/token-filter.properties")
public class AccessTokenFilterConfig {

}
