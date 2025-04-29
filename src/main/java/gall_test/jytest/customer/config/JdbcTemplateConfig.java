package gall_test.jytest.customer.config;


import gall_test.jytest.customer.CustomerService;
import gall_test.jytest.customer.repository.CustomerRepository;
import gall_test.jytest.customer.repository.jdbctemplate.JdbcTemplateCustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    private final DataSource dataSource;

    public JdbcTemplateConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new JdbcTemplateCustomerRepository(dataSource);
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService(customerRepository());
    }

    @Bean
    public CustomerRepository accountRepository() {
        return new JdbcTemplateCustomerRepository(dataSource);
    }

    @Bean
    public CustomerService accountService() {
        return new CustomerService(customerRepository());
    }
}
