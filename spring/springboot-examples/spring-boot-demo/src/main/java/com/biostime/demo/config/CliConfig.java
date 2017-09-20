package com.biostime.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * DruidConfig
 *
 * @author 12465
 * @version 1.0
 * @createDate 2016/7/18 10:04
 */
@Configuration
@Data
public class CliConfig {
	
	public static final String RESOURCES = "classpath:/application.properties,classpath:/config.properties,classpath:/jdbc-public.properties,classpath:/redis-public.properties";

	@Bean
	public List<String> integrCfgNoReloadableResources() {
		List<String> integrCfgNoReloadableResourcesList = new ArrayList<>();
		String[] cfgResources = RESOURCES.split(",");
		if (cfgResources != null && cfgResources.length != 0) {
			for (String cfgResource : cfgResources) {
				integrCfgNoReloadableResourcesList.add(cfgResource);
			}
		}
		return integrCfgNoReloadableResourcesList;
	}
}
