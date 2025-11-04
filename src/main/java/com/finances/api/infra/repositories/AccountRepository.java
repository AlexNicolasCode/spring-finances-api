package com.finances.api.infra.repositories;

import com.finances.api.data.protocols.checkAccountByIdRepository.ICheckAccountByIdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountRepository implements ICheckAccountByIdRepository {
    private final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

    @Autowired
    private CrudAccountRepository repo;

    public boolean checkAccountById(UUID accountId) {
        try {
            return this.repo.existsById(accountId);
        } catch (Exception error) {
            this.logger.error(error.getMessage());
            return false;
        }
    }
}
