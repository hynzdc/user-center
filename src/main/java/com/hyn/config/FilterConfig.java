package com.hyn.config;

import com.hyn.filter.XssFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

/**
 * Filter配置
 * @author mumu
 * @date 2020/5/6 11:34 上午
 */
@Configuration
public class FilterConfig {

	@Value("${xss.enabled}")
	private String enabled;

	@Value("${xss.excludes}")
	private String excludes;

	@Value("${xss.urlPatterns}")
	private String urlPatterns;

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Bean
	public FilterRegistrationBean xssFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		registration.setFilter(new XssFilter());
		registration.addUrlPatterns(StringUtils.split(urlPatterns, ","));
		registration.setName("xssFilter");
		registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("excludes", excludes);
		initParameters.put("enabled", enabled);
		registration.setInitParameters(initParameters);
		return registration;
	}

}
