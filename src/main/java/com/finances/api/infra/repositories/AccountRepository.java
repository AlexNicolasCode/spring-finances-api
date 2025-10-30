package com.finances.api.infra.repositories;

import com.finances.api.data.protocols.checkAccountByIdRepository.ICheckAccountByIdRepository;

import java.util.UUID;

public class AccountRepository implements ICheckAccountByIdRepository {

    public boolean checkAccountById(UUID accountId) {
        return true;
    }
}
