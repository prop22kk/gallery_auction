package gall_test.jytest.repository;

import gall_test.jytest.customer.Customer;
import gall_test.jytest.customer.CustomerSearchCondition;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(final Customer customer);

    Optional<Customer> findById(final Long id);

    List<Customer> findAll();

    int update(final Customer customer);

    int delete(final Long id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phone_number);

    List<Customer> search(CustomerSearchCondition cond);
}