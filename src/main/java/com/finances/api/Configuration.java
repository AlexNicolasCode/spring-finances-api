package com.finances.api;

import com.finances.api.data.protocols.checkAccountByIdRepository.ICheckAccountByIdRepository;
import com.finances.api.data.protocols.createTransactionRepository.ICreateTransactionRepository;
import com.finances.api.data.services.AccountService;
import com.finances.api.data.services.TransactionService;
import com.finances.api.infra.repositories.TransactionRepository;
import com.finances.api.services.protocols.checkAccountByIdService.ICheckAccountByIdService;
import com.finances.api.infra.repositories.AccountRepository;
import com.finances.api.services.protocols.createTransactionService.ICreateTransactionService;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ICheckAccountByIdRepository getCheckAccountByIdRepository() {
        return new AccountRepository();
    }

    @Bean
    public ICheckAccountByIdService getCheckAccountById() {
        return new AccountService(this.getCheckAccountByIdRepository());
    }

    @Bean
    public ICreateTransactionRepository getCreateTransactionRepository() {
        return new TransactionRepository();
    }

    @Bean
    public ICreateTransactionService getCreateTransactionService() {
        return new TransactionService(this.getCreateTransactionRepository());
    }
}
