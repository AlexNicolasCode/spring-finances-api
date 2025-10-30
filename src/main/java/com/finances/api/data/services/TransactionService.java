package com.finances.api.data.services;

import com.finances.api.data.protocols.createTransactionRepository.CreateTransactionRepositoryDto;
import com.finances.api.data.protocols.createTransactionRepository.ICreateTransactionRepository;
import com.finances.api.services.protocols.createTransactionService.CreateTransactionServiceDto;
import com.finances.api.services.protocols.createTransactionService.ICreateTransactionService;

public class TransactionService implements ICreateTransactionService {
    private final ICreateTransactionRepository createTransactionRepository;

    public TransactionService(
            ICreateTransactionRepository createTransactionRepository
    ) {
        this.createTransactionRepository = createTransactionRepository;
    }

    public void createTransaction(CreateTransactionServiceDto dto) {
        var repositoryDto = new CreateTransactionRepositoryDto(
                    dto.value(),
                    dto.type(),
                    dto.fromAccountId(),
                    dto.targetAccountId()
            );
        this.createTransactionRepository.createTransaction(repositoryDto);
    }
}
