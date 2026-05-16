package com.ridex.ridex.repository;

import com.ridex.ridex.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customer by email
    Optional<Customer> findByEmail(String email);

    // Find customer by phone
    Optional<Customer> findByPhone(String phone);

    // Search customers by name
    List<Customer> findByFullNameContainingIgnoreCase(String name);

    // Count active customers
    long countByStatus(Customer.CustomerStatus status);
}