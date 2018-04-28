package com.gudderi.api.component.configuration;

import com.gudderi.api.component.context.HolderStrategy;
import com.gudderi.api.component.context.ThreadLocalHolderStrategy;
import com.gudderi.api.filter.ExecContextFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class ExecContextConfiguration {
    @Bean
    public FilterRegistrationBean execContextFilter() {
        FilterRegistrationBean<ExecContextFilter> bean = new FilterRegistrationBean<>();
        HolderStrategy strategy = new ThreadLocalHolderStrategy();
        bean.setFilter(new ExecContextFilter("API", strategy));
        // ExceptionHandlerFilterの後に動く
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        return bean;
    }
}
