package gall_test.jytest.repository.jdbctemplate;

import gall_test.jytest.item.Item;
import gall_test.jytest.item.ItemSearchCondition;
import gall_test.jytest.repository.ItemRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateItemRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateItemRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        String sql = "INSERT INTO items (artist, item_name, starting_price, auction_start, auction_end, category, owner_id, current_price, image_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"item_id"});
            ps.setString(1, item.getArtist());
            ps.setString(2, item.getItemName());
            ps.setDouble(3, item.getStartingPrice());
            ps.setObject(4, item.getAuctionStart());
            ps.setObject(5, item.getAuctionEnd());
            ps.setString(6, item.getCategory());
            ps.setObject(7, item.getOwnerId());
            ps.setObject(8, item.getCurrentPrice());
            ps.setString(9, item.getImageUrl());
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue();
        item.setItemId(key);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "SELECT * FROM items WHERE item_id = ?";
        try {
            Item item = jdbcTemplate.queryForObject(sql, rowMapper(), id);
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int update(Item item) {
        String sql = "UPDATE items SET artist = ?, item_name = ?, starting_price = ?, auction_start = ?, auction_end = ?, category = ?, owner_id = ?, current_price = ?, image_url = ? WHERE item_id = ?";
        return jdbcTemplate.update(sql,
                item.getArtist(),
                item.getItemName(),
                item.getStartingPrice(),
                item.getAuctionStart(),
                item.getAuctionEnd(),
                item.getCategory(),
                item.getOwnerId(),
                item.getCurrentPrice(),
                item.getImageUrl(),
                item.getItemId()
        );
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM items WHERE item_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Item> search(ItemSearchCondition cond) {
        String sql = "SELECT * FROM items WHERE 1 = 1";
        List<Object> params = new ArrayList<>();

        if (cond.getArtist() != null) {
            sql += " AND artist LIKE ?";
            params.add("%" + cond.getArtist() + "%");
        }

        if (cond.getItemName() != null) {
            sql += " AND item_name LIKE ?";
            params.add("%" + cond.getItemName() + "%");
        }

        if (cond.getCategory() != null) {
            sql += " AND category LIKE ?";
            params.add("%" + cond.getCategory() + "%");
        }

        return jdbcTemplate.query(sql, rowMapper(), params.toArray());
    }

    private RowMapper<Item> rowMapper() {
        return (rs, rowNum) -> {
            Item item = new Item();
            item.setItemId(rs.getLong("item_id"));
            item.setArtist(rs.getString("artist"));
            item.setItemName(rs.getString("item_name"));
            item.setStartingPrice(rs.getDouble("starting_price"));
            item.setAuctionStart(rs.getTimestamp("auction_start").toLocalDateTime());
            item.setAuctionEnd(rs.getTimestamp("auction_end").toLocalDateTime());
            item.setCategory(rs.getString("category"));
            item.setOwnerId(rs.getLong("owner_id"));
            item.setCurrentPrice(rs.getDouble("current_price"));
            item.setImageUrl(rs.getString("image_url")); // ✅ 여기 추가
            return item;
        };
    }
}
