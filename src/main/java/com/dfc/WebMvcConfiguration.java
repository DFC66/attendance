package com.dfc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.dfc.constant.DataConstant.PATH_SPLIT;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    @Value("${file-service.profile}")
    private String homePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/face/**").addResourceLocations("file:"+this.homePath+PATH_SPLIT);
        super.addResourceHandlers(registry);
    }
}
