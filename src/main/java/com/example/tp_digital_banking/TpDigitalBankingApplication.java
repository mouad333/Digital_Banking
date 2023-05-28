package com.example.tp_digital_banking;

import com.example.tp_digital_banking.entities.AccountOperation;
import com.example.tp_digital_banking.entities.CurrentAccount;
import com.example.tp_digital_banking.entities.Customer;
import com.example.tp_digital_banking.entities.SavingAccount;
import com.example.tp_digital_banking.enums.AccountStatus;
import com.example.tp_digital_banking.enums.OperationType;
import com.example.tp_digital_banking.repositories.AccountOperationRepository;
import com.example.tp_digital_banking.repositories.BankAccountRepository;
import com.example.tp_digital_banking.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class TpDigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpDigitalBankingApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Hassan","Fayrouz","Mouad").forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });

                customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount=new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()+90000);
                currentAccount.setCreateDate(new Date());
                currentAccount.setAccountStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverdraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()+90000);
                savingAccount.setCreateDate(new Date());
                savingAccount.setCustomer(customer);
                savingAccount.setAccountStatus(AccountStatus.CREATED);
                savingAccount.setInterestRate(5.9);
                bankAccountRepository.save(savingAccount);
            });
                bankAccountRepository.findAll().forEach(bankAccount -> {
                    for (int i=0; i<10;i++) {
                        AccountOperation accountOperation = new AccountOperation();
                        accountOperation.setBankAccount(bankAccount);
                        accountOperation.setOperationDate(new Date());
                        accountOperation.setAmount(Math.random() + 90000);
                        accountOperation.setOperationType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    }
                });
        };
    }
}
