package com.finances.api.presentation.controllers;

import com.finances.api.domain.usecases.createTransaction.ICreateTransactionUseCase;
import com.finances.api.domain.usecases.loadAccountsByUserId.ILoadAccountsByUserIdUseCase;
import com.finances.api.domain.usecases.loadAccountsByUserId.LoadAccountsByUserIdUseCaseOutputDto;
import com.finances.api.domain.usecases.loadTransactions.LoadTransactionsUseCaseInputDto;
import com.finances.api.domain.usecases.loadTransactions.LoadTransactionsUseCaseOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class LoadAccountsByUserIdController {
    private final ILoadAccountsByUserIdUseCase loadAccountsByUserIdUseCase;

    public LoadAccountsByUserIdController(
            ILoadAccountsByUserIdUseCase loadAccountsByUserIdUseCase
    ) {
        this.loadAccountsByUserIdUseCase = loadAccountsByUserIdUseCase;
    }

    @GetMapping
    public List<LoadAccountsByUserIdUseCaseOutputDto> loadAccountsByUserId(
            @RequestHeader("userId") UUID userId
    ) {
        return this.loadAccountsByUserIdUseCase.loadAccountsByUserId(userId);
    }
}
