package gall_test.jytest.config;


import gall_test.jytest.item.ItemService;
import gall_test.jytest.repository.ItemRepository;
import gall_test.jytest.customer.CustomerService;
import gall_test.jytest.repository.CustomerRepository;
import gall_test.jytest.repository.jdbctemplate.JdbcTemplateCustomerRepository;
import gall_test.jytest.repository.jdbctemplate.JdbcTemplateItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public ItemRepository itemRepository() {
        return new JdbcTemplateItemRepository(dataSource);
    }

    // 🔹 ItemService Bean 등록
    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository());
    }


}
