package gall_test.jytest.customer;

public class CustomerRequest {

    private String address;
    private String phone_number;
    private String email;

    public CustomerRequest() {
    }

    public CustomerRequest(String address, String phone_number, String email) {
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphone() {
        return phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}