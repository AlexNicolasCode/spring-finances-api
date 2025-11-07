package com.finances.api.data.services;

import com.finances.api.data.protocols.createTransactionRepository.CreateTransactionRepositoryDto;
import com.finances.api.data.protocols.createTransactionRepository.ICreateTransactionRepository;
import com.finances.api.data.protocols.loadTransactionByIdRepository.ILoadTransactionByIdRepository;
import com.finances.api.data.protocols.loadTransactionByIdRepository.LoadTransactionByIdRepositoryOutputDto;
import com.finances.api.data.protocols.loadTransactionsRepository.ILoadTransactionsRepository;
import com.finances.api.data.protocols.loadTransactionsRepository.LoadTransactionsRepositoryOutputDto;
import com.finances.api.services.protocols.createTransactionService.CreateTransactionServiceDto;
import com.finances.api.services.protocols.createTransactionService.ICreateTransactionService;
import com.finances.api.services.protocols.loadTransactionByIdService.ILoadTransactionByIdService;
import com.finances.api.services.protocols.loadTransactionByIdService.LoadTransactionByIdServiceOutputDto;
import com.finances.api.services.protocols.loadTransactionsService.ILoadTransactionsService;
import com.finances.api.services.protocols.loadTransactionsService.LoadTransactionsServiceOutputDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService implements ICreateTransactionService, ILoadTransactionsService, ILoadTransactionByIdService {
    private final ICreateTransactionRepository createTransactionRepository;
    private final ILoadTransactionsRepository loadTransactionsRepository;
    private final ILoadTransactionByIdRepository loadTransactionByIdRepository;

    public TransactionService(
            ICreateTransactionRepository createTransactionRepository,
            ILoadTransactionsRepository loadTransactionsRepository,
            ILoadTransactionByIdRepository loadTransactionByIdRepository
    ) {
        this.createTransactionRepository = createTransactionRepository;
        this.loadTransactionsRepository = loadTransactionsRepository;
        this.loadTransactionByIdRepository = loadTransactionByIdRepository;
    }

    public UUID createTransaction(CreateTransactionServiceDto dto) {
        CreateTransactionRepositoryDto repositoryDto = new CreateTransactionRepositoryDto(
                    dto.value(),
                    dto.type(),
                    dto.fromAccountId(),
                    dto.targetAccountId()
            );
        return this.createTransactionRepository.createTransaction(repositoryDto);
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

    public LoadTransactionByIdServiceOutputDto loadTransactionById(UUID transactionId) throws Exception {
        LoadTransactionByIdRepositoryOutputDto transaction = this.loadTransactionByIdRepository.loadTransactionById(transactionId);
        return new LoadTransactionByIdServiceOutputDto(
                transaction.id(),
                transaction.type(),
                transaction.status(),
                transaction.value(),
                transaction.fromAccountId(),
                transaction.targetAccountId()
        );
    }
}
