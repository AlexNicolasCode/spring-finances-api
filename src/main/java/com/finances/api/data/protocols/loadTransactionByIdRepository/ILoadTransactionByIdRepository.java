package com.finances.api.data.protocols.loadTransactionByIdRepository;

import java.util.UUID;

public interface ILoadTransactionByIdRepository {
    LoadTransactionByIdRepositoryOutputDto loadTransactionById(UUID transactionId) throws Exception;
}
