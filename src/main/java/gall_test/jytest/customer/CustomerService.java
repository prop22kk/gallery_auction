package gall_test.jytest.customer;

import gall_test.jytest.customer.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    Customer createCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    List<Customer> findCustomers() {
        return customerRepository.findAll() ;
    }
    Optional<Customer> findCustomerById(final Long id) {
        return customerRepository.findById(id);
    }

    public int updateCustomer(final Customer customer) {
        return customerRepository.update(customer);
    }

    public int deleteCustomer(final Long id) {
        return customerRepository.delete(id);
    }




}
