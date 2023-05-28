package com.example.tp_digital_banking.repositories;

import com.example.tp_digital_banking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
