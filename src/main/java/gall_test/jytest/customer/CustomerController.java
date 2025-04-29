package gall_test.jytest.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customerstest")

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }
    // 고객 추가 API-POST
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody final CustomerRequest request) {
        Customer customer = new Customer(
                request.getAddress(), request.getEmail(), request.getCellphone());
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }
    //전체 고객 검색
    @GetMapping
    public ResponseEntity<List<Customer>>getCustomers() {
        return ResponseEntity.ok(customerService.findCustomers());

    }

    //id로 고객 검색
    @GetMapping("/{customer_id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customer_id") final Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id).get()) ;
    }

    // 고객 정보 수정
    @PatchMapping("/{customer_id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("customer_id") final Long id,
                                                 @RequestBody final CustomerRequest request) {
        Customer updateCustomer = new Customer(
                request.getAddress(), request.getEmail(), request.getCellphone());
        customerService.updateCustomer(updateCustomer);
        return ResponseEntity.ok("업데이트 성공");
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<String> removeCustomer(@PathVariable("customer_id") final Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("고객 정보 삭제 성공");
    }


}