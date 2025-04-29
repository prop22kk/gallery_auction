package gall_test.jytest.customer;

import java.util.Objects;

public class Customer {
    private Long id;
    private String address;
    private String phone_number;  // 필드명을 'phone_number'로 유지
    private String email;

    public Customer() {}

    public Customer(String address, String phone_number, String email) {
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Customer(Long id, String address, String phone_number, String email) {
        this.id = id;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone_number() { return phone_number; }  // getter에서 'phone_number' 사용
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }  // setter에서 'phone_number' 사용
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +  // toString에서 'phone_number' 사용
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(phone_number, customer.phone_number) &&  // equals에서 'phone_number' 사용
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, phone_number, email);  // hashCode에서 'phone_number' 사용
    }
}
