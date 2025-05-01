package auction.config;

import auction.item.ItemService;

import auction.repository.ItemRepository;
import auction.repository.jdbctemplate.JdbcTemplateItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    private final DataSource dataSource;

    public JdbcTemplateConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 🔹 ItemRepository Bean 등록
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
