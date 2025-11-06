package com.finances.api.data.protocols.createTransactionRepository;

import java.util.UUID;

public interface ICreateTransactionRepository {
    UUID createTransaction(CreateTransactionRepositoryDto dto);
}
