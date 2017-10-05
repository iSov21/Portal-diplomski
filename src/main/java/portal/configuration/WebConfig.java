package portal.configuration;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages="portal")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	
	private final Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	@Bean
	public ViewResolver viewResolver() {
	 InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
	 internalResourceViewResolver.setViewClass(JstlView.class);
	 internalResourceViewResolver.setPrefix("/WEB-INF/views/");
	 internalResourceViewResolver.setSuffix(".jsp");
	 return internalResourceViewResolver;
	}
	
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
                        .addResourceLocations("/WEB-INF/resources/");
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

	    // http
	    HttpMessageConverter converter = new StringHttpMessageConverter();
	    converters.add(converter);
	    logger.info("HttpMessageConverter added");

	    // string
	    converter = new FormHttpMessageConverter();
	    converters.add(converter);
	    logger.info("FormHttpMessageConverter added");

	    // json
	    converter = new MappingJackson2HttpMessageConverter();
	    converters.add(converter);
	    logger.info("MappingJackson2HttpMessageConverter added");
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return new CommonsMultipartResolver();
	}
}
