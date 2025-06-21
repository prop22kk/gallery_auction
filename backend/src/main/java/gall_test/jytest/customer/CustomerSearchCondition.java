package gall_test.jytest.customer;

public class CustomerSearchCondition {
    private Long id;
    private String address;
    private String phone_number;
    private String email;
    private String customer_name;

    public CustomerSearchCondition(Long id, String address, String phone_number, String email, String customer_name) {
        this.id = id;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.customer_name = customer_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
