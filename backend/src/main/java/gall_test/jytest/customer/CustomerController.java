package gall_test.jytest.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customerstest")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    // 고객 추가 API - POST
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody final CustomerRequest request) {
        Customer customer = new Customer(
                request.getAddress(), request.getPhone_number(), request.getEmail());
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    // 전체 고객 검색 - GET
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.findCustomers());
    }

    // id로 고객 검색 - GET
    @GetMapping("/{customer_id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customer_id") final Long id) {
        Optional<Customer> customerOpt = customerService.findCustomerById(id);
        if (customerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerOpt.get());
    }

    // 검색 조건으로 고객 검색 - GET
    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(CustomerSearchCondition cond) {
        return ResponseEntity.ok(customerService.search(cond));
    }

    // 고객 정보 수정 - PATCH
    @PatchMapping("/{customer_id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("customer_id") final Long id,
                                                 @RequestBody final CustomerRequest request) {
        Customer updateCustomer = new Customer();
        updateCustomer.setId(id);
        updateCustomer.setAddress(request.getAddress());
        updateCustomer.setEmail(request.getEmail());
        updateCustomer.setPhone_number(request.getPhone_number());

        int updatedCount = customerService.updateCustomer(updateCustomer);
        if (updatedCount == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 회원을 찾을 수 없거나 수정된 데이터가 없습니다.");
        }
        return ResponseEntity.ok("업데이트 성공");
    }

    // 고객 삭제 - DELETE
    @DeleteMapping("/{customer_id}")
    public ResponseEntity<String> removeCustomer(@PathVariable("customer_id") final Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("고객 정보 삭제 성공");
    }

}
