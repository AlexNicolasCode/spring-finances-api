package com.finances.api.data.protocols.checkAccountByIdRepository;

import java.util.UUID;

public interface ICheckAccountByIdRepository {
    boolean checkAccountById(UUID accountId);
}
