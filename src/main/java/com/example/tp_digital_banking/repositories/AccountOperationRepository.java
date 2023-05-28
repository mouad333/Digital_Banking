package com.example.tp_digital_banking.repositories;

import com.example.tp_digital_banking.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {

}
