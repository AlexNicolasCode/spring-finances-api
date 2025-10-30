package com.finances.api.data.services;

import com.finances.api.data.protocols.checkAccountByIdRepository.ICheckAccountByIdRepository;
import com.finances.api.services.protocols.checkAccountByIdService.ICheckAccountByIdService;

import java.util.UUID;

public class AccountService implements ICheckAccountByIdService {
    private final ICheckAccountByIdRepository checkAccountByIdRepository;

    public AccountService(
            ICheckAccountByIdRepository checkAccountByIdRepository
    ) {
        this.checkAccountByIdRepository = checkAccountByIdRepository;
    }

    public boolean checkAccountById(UUID accountId) {
        return this.checkAccountByIdRepository.checkAccountById(accountId);
    }
}
