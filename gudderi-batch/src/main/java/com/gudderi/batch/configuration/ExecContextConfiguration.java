package com.gudderi.batch.configuration;

import com.gudderi.core.component.context.HolderStrategy;
import com.gudderi.core.component.context.ThreadLocalHolderStrategy;
import com.gudderi.core.filter.ExecContextFilter;

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
        bean.setFilter(new ExecContextFilter("BATCH", strategy));
        // ExceptionHandlerFilterの後に動く
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        return bean;
    }
}
