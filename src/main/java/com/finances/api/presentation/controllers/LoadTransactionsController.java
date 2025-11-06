package com.finances.api.presentation.controllers;

import com.finances.api.domain.usecases.createTransaction.CreateTransactionUseCaseDto;
import com.finances.api.domain.usecases.createTransaction.ICreateTransactionUseCase;
import com.finances.api.domain.usecases.loadTransactions.ILoadTransactionsUseCase;
import com.finances.api.domain.usecases.loadTransactions.LoadTransactionsUseCaseInputDto;
import com.finances.api.domain.usecases.loadTransactions.LoadTransactionsUseCaseOutputDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class LoadTransactionsController {
    private final ILoadTransactionsUseCase loadTransactionsUseCase;

    public LoadTransactionsController(
            ILoadTransactionsUseCase loadTransactionsUseCase
    ) {
        this.loadTransactionsUseCase = loadTransactionsUseCase;
    }

    @GetMapping
    public List<LoadTransactionsUseCaseOutputDto> loadTransactions(@RequestParam("accountId") UUID accountId) {
        System.out.println(accountId.toString());
        LoadTransactionsUseCaseInputDto input = new LoadTransactionsUseCaseInputDto(accountId);
        return this.loadTransactionsUseCase.loadTransactions(input);
    }
}
