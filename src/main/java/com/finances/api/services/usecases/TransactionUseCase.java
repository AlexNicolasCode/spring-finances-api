package com.finances.api.services.usecases;

import com.finances.api.domain.usecases.loadTransactions.ILoadTransactionsUseCase;
import com.finances.api.domain.usecases.loadTransactions.LoadTransactionsUseCaseInputDto;
import com.finances.api.domain.usecases.loadTransactions.LoadTransactionsUseCaseOutputDto;
import com.finances.api.services.protocols.createTransactionService.CreateTransactionServiceDto;
import com.finances.api.services.protocols.createTransactionService.ICreateTransactionService;
import com.finances.api.services.protocols.checkAccountByIdService.ICheckAccountByIdService;
import com.finances.api.domain.usecases.createTransaction.CreateTransactionUseCaseDto;
import com.finances.api.domain.usecases.createTransaction.ICreateTransactionUseCase;
import com.finances.api.services.protocols.loadTransactionsService.ILoadTransactionsService;
import com.finances.api.services.protocols.loadTransactionsService.LoadTransactionsServiceOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionUseCase implements ICreateTransactionUseCase, ILoadTransactionsUseCase {
    private final ICheckAccountByIdService checkAccountByIdService;
    private final ICreateTransactionService createTransactionService;
    private final ILoadTransactionsService loadTransactionsService;

    public TransactionUseCase(
            ICheckAccountByIdService checkAccountByIdService,
            ICreateTransactionService createTransactionService,
            ILoadTransactionsService loadTransactionsService
    ) {
        this.checkAccountByIdService = checkAccountByIdService;
        this.createTransactionService = createTransactionService;
        this.loadTransactionsService = loadTransactionsService;
    }

    public List<LoadTransactionsUseCaseOutputDto> loadTransactions(LoadTransactionsUseCaseInputDto dto) {
        List<LoadTransactionsServiceOutputDto> transactions =  this.loadTransactionsService.loadTransactions(dto.accountId());
        return this.buildResponse(transactions);
    }

    private List<LoadTransactionsUseCaseOutputDto> buildResponse(List<LoadTransactionsServiceOutputDto> transactions) {
        List<LoadTransactionsUseCaseOutputDto> response = new ArrayList<>();
        for (LoadTransactionsServiceOutputDto transaction : transactions) {
            response.add(
                    new LoadTransactionsUseCaseOutputDto(
                            transaction.id(),
                            transaction.accountName(),
                            transaction.type(),
                            transaction.value()
                    )
            );
        }
        return response;
    }

    public void createTransaction(CreateTransactionUseCaseDto dto) {
        boolean isInvalidTransaction = dto.fromAccountId().equals(dto.targetAccountId());
        if (isInvalidTransaction) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account IDs are not the same");
        }
        boolean hasFromAccount =
                this.checkAccountByIdService.checkAccountById(dto.fromAccountId());
        if (!hasFromAccount) {
            throw buildNotFoundExpectionAccount(dto.fromAccountId());
        }
        boolean hasTargetAccount =
                this.checkAccountByIdService.checkAccountById(dto.targetAccountId());
        if (!hasTargetAccount) {
            throw buildNotFoundExpectionAccount(dto.targetAccountId());
        }
        var transaction = new CreateTransactionServiceDto(
                    dto.value(),
                    dto.type(),
                    dto.fromAccountId(),
                    dto.targetAccountId()
                );
        this.createTransactionService.createTransaction(transaction);
    }

    private ResponseStatusException buildNotFoundExpectionAccount(UUID accountId) {
        String message = String.format("Account %s is invalid", accountId);
        return new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}
