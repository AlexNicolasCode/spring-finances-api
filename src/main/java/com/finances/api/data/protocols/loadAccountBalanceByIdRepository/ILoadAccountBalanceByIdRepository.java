package com.finances.api.data.protocols.loadAccountBalanceByIdRepository;

import java.util.UUID;

public interface ILoadAccountBalanceByIdRepository {
    LoadAccountBalanceByIdRepositoryOutputDto loadAccountBalanceById(UUID accountId);
}
