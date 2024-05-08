package work.umatech.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.umatech.security.filters.AuthFilter;
import work.umatech.security.filters.LogFilter;

@Configuration
public class FilterConfig {

    @Autowired
    private LogFilter logFilter;

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public FilterRegistrationBean<LogFilter> LogFilterRegistration() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(logFilter);
        registrationBean.addUrlPatterns("/*"); // Apply the filter to all URLs
        registrationBean.setOrder(1); // Set the order of the filter
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<AuthFilter> AuthFilterRegistration() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/*"); // Apply the filter to all URLs
        registrationBean.setOrder(2); // Set the order of the filter
        return registrationBean;
    }


}
