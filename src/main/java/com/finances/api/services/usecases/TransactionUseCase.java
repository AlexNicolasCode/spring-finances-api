package com.finances.api.services.usecases;

import com.finances.api.services.protocols.createTransactionService.CreateTransactionServiceDto;
import com.finances.api.services.protocols.createTransactionService.ICreateTransactionService;
import com.finances.api.services.protocols.checkAccountByIdService.ICheckAccountByIdService;
import com.finances.api.domain.usecases.createTransaction.CreateTransactionUseCaseDto;
import com.finances.api.domain.usecases.createTransaction.ICreateTransactionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class TransactionUseCase implements ICreateTransactionUseCase {
    private final ICheckAccountByIdService checkAccountByIdService;
    private final ICreateTransactionService createTransactionService;

    public TransactionUseCase(
            ICheckAccountByIdService checkAccountByIdService,
            ICreateTransactionService createTransactionService
    ) {
        this.checkAccountByIdService = checkAccountByIdService;
        this.createTransactionService = createTransactionService;
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
