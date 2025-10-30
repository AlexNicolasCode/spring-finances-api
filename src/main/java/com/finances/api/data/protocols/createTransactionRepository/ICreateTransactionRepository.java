package com.finances.api.data.protocols.createTransactionRepository;

import com.finances.api.data.protocols.createTransactionRepository.CreateTransactionRepositoryDto;

public interface ICreateTransactionRepository {
    void createTransaction(CreateTransactionRepositoryDto dto);
}
