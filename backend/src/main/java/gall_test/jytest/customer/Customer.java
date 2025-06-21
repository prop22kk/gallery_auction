package gall_test.jytest.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Customer {
    private Long id;
    private String address;
    private String customer_name;

    @JsonProperty("phone_number")
    private String phone_number;  // 필드명을 'phone_number'로 유지
    private String email;

    public Customer() {}

    public Customer(String address, String phone_number, String email, String customer_name) {
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.customer_name = customer_name;
    }

    public Customer(Long id, String address, String phone_number, String email, String customer_name) {
        this.id = id;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.customer_name = customer_name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCustomer_name() { return customer_name; }
    public void setCustomer_name(String customer_name) { this.customer_name = customer_name; }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", name='" + customer_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(phone_number, customer.phone_number) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(customer_name, customer.customer_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, phone_number, email, customer_name); }
}
