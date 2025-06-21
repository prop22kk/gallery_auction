package gall_test.jytest.repository.jdbctemplate;

import gall_test.jytest.activities.Activity;
import gall_test.jytest.repository.ActivityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateActivityRepository implements ActivityRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateActivityRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Activity save(Activity activity) {
        String sql = "INSERT INTO activities (member_id, item_id, price) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"activity_id"});
            ps.setString(1, activity.getMemberId());
            ps.setLong(2, activity.getItemId());
            ps.setDouble(3, activity.getPrice());
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue();
        activity.setActivityId(key);
        return activity;
    }

    @Override
    public List<Activity> findAll() {
        String sql = "SELECT * FROM activities ORDER BY activity_time DESC";
        return jdbcTemplate.query(sql, rowMapper());
    }

    @Override
    public List<Activity> findByMemberId(String memberId) {
        String sql = "SELECT * FROM activities WHERE member_id = ?";
        return jdbcTemplate.query(sql, rowMapper(), memberId);
    }

    @Override
    public List<Activity> findByItemId(Long itemId) {
        String sql = "SELECT * FROM activities WHERE item_id = ?";
        return jdbcTemplate.query(sql, rowMapper(), itemId);
    }

    private RowMapper<Activity> rowMapper() {
        return (rs, rowNum) -> new Activity(
                rs.getLong("activity_id"),
                rs.getString("member_id"),
                rs.getLong("item_id"),
                rs.getDouble("price"),
                rs.getTimestamp("activity_time")
        );
    }

    @Override
    public Optional<Activity> findById(Long id) {
        String sql = "SELECT * FROM activities WHERE activity_id = ?";
        try {
            Activity activity = jdbcTemplate.queryForObject(sql, rowMapper(), id);
            return Optional.of(activity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
