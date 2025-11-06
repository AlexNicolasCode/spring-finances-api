package com.finances.api.data.services;

import com.finances.api.data.protocols.createTransactionRepository.CreateTransactionRepositoryDto;
import com.finances.api.data.protocols.createTransactionRepository.ICreateTransactionRepository;
import com.finances.api.data.protocols.loadTransactionsRepository.ILoadTransactionsRepository;
import com.finances.api.data.protocols.loadTransactionsRepository.LoadTransactionsRepositoryOutputDto;
import com.finances.api.services.protocols.createTransactionService.CreateTransactionServiceDto;
import com.finances.api.services.protocols.createTransactionService.ICreateTransactionService;
import com.finances.api.services.protocols.loadTransactionsService.ILoadTransactionsService;
import com.finances.api.services.protocols.loadTransactionsService.LoadTransactionsServiceOutputDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService implements ICreateTransactionService, ILoadTransactionsService {
    private final ICreateTransactionRepository createTransactionRepository;
    private final ILoadTransactionsRepository loadTransactionsRepository;

    public TransactionService(
            ICreateTransactionRepository createTransactionRepository,
            ILoadTransactionsRepository loadTransactionsRepository
    ) {
        this.createTransactionRepository = createTransactionRepository;
        this.loadTransactionsRepository = loadTransactionsRepository;
    }

    public UUID createTransaction(CreateTransactionServiceDto dto) {
        CreateTransactionRepositoryDto repositoryDto = new CreateTransactionRepositoryDto(
                    dto.value(),
                    dto.type(),
                    dto.fromAccountId(),
                    dto.targetAccountId()
            );
        UUID transactionId = this.createTransactionRepository.createTransaction(repositoryDto);
        return transactionId;
    }

    public List<LoadTransactionsServiceOutputDto> loadTransactions(UUID accountId, String search) {
        List<LoadTransactionsRepositoryOutputDto> transactions = this.loadTransactionsRepository.loadTransactions(1, 30, accountId, search);
        return this.buildResponse(transactions);
    }

    private List<LoadTransactionsServiceOutputDto> buildResponse(List<LoadTransactionsRepositoryOutputDto> transactions) {
        List<LoadTransactionsServiceOutputDto> response = new ArrayList<>();
        for (LoadTransactionsRepositoryOutputDto transaction : transactions) {
            response.add(
                    new LoadTransactionsServiceOutputDto(
                            transaction.id(),
                            transaction.accountName(),
                            transaction.type(),
                            transaction.status(),
                            transaction.value(),
                            transaction.isSender()
                    )
            );
        }
        return response;
    }
}
