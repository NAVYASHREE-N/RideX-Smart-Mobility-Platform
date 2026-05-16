package com.ridex.ridex.controller;

import com.ridex.ridex.entity.Customer;
import com.ridex.ridex.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
    }

    // Register new customer
    @PostMapping
    public Customer registerCustomer(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }

    // Update customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully!";
    }

    // Search customers
    @GetMapping("/search/{name}")
    public List<Customer> searchCustomers(@PathVariable String name) {
        return customerService.searchCustomers(name);
    }

    // Count active customers
    @GetMapping("/count/active")
    public long countActiveCustomers() {
        return customerService.countActiveCustomers();
    }
}
