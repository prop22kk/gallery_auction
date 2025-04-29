package gall_test.jytest.customer.repository;

import gall_test.jytest.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(final Customer customer);

    Optional<Customer> findById(final Long id);

    List<Customer> findAll();

    int update(final Customer customer);

    int delete(final Long id);
}