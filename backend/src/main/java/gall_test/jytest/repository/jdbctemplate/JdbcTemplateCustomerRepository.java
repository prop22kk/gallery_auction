package gall_test.jytest.repository.jdbctemplate;

import gall_test.jytest.customer.Customer;
import gall_test.jytest.customer.CustomerSearchCondition;
import gall_test.jytest.item.Item;
import gall_test.jytest.repository.CustomerRepository;
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

public class JdbcTemplateCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCustomerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer save(Customer customer) {
        String sql ="INSERT INTO customer (address, phone_number, email) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement psmt = connection.prepareStatement(sql, new String[]{"customer_id"});
            psmt.setString(1, customer.getAddress());
            psmt.setString(2, customer.getPhone_number());
            psmt.setString(3, customer.getEmail());
            return psmt;
        },keyHolder);
        long key = keyHolder.getKey().longValue();
        customer.setId(key);
        return customer;
    }
    @Override
public Optional<Customer> findById(Long id) {
    String sql ="SELECT * FROM customer WHERE customer_id = ?";
    try {
        Customer customer = jdbcTemplate.queryForObject(sql,rowMapper(),id);
        return Optional.of(customer);
    } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
    }
    }
    @Override
    public List<Customer> findAll() {
        String sql ="SELECT * FROM customer";
        return jdbcTemplate.query(sql,rowMapper());
    }
    private RowMapper<Customer> rowMapper() {
        return(rs,rowNum) -> new Customer(
                rs.getLong("customer_id"),
                rs.getString("address"),
                rs.getString("phone_number"),
                rs.getString("email")
        );
    }

    @Override
    public int update(Customer customer) {
        String sql = "UPDATE customer SET address = ?, email = ?, phone_number = ? WHERE customer_id = ?";
        return jdbcTemplate.update(sql,customer.getAddress(),customer.getEmail(),customer.getPhone_number(),customer.getId());
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        try {
            Customer customer = jdbcTemplate.queryForObject(sql, rowMapper(), email);
            return Optional.of(customer);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String phone_number) {
        String sql = "SELECT * FROM customer WHERE phone_number = ?";
        try {
            Customer customer = jdbcTemplate.queryForObject(sql, rowMapper(), phone_number);
            return Optional.of(customer);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> search(CustomerSearchCondition cond) {
        String sql = "SELECT * FROM customer WHERE 1 = 1";
        List<Object> params = new ArrayList<>();

        if (cond.getAddress() != null) {
            sql += " AND address LIKE ?";
            params.add("%" + cond.getAddress() + "%");
        }

        if (cond.getPhone_number() != null) {
            sql += " AND phone_number LIKE ?";
            params.add("%" + cond.getPhone_number() + "%");
        }

        if (cond.getEmail() != null) {
            sql += " AND email LIKE ?";
            params.add("%" + cond.getEmail() + "%");
        }


        return jdbcTemplate.query(sql, rowMapper(), params.toArray());
    }



}


