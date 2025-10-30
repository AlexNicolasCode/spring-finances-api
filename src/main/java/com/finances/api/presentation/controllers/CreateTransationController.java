package com.finances.api.presentation.controllers;

import com.finances.api.domain.usecases.createTransaction.CreateTransactionUseCaseDto;
import com.finances.api.domain.usecases.createTransaction.ICreateTransactionUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class CreateTransationController {
    private final ICreateTransactionUseCase createTransactionUseCase;

    public CreateTransationController(
            ICreateTransactionUseCase createTransactionUseCase
    ) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@Valid @RequestBody CreateTransactionUseCaseDto dto) {
        System.out.println(dto.toString());
        this.createTransactionUseCase.createTransaction(dto);
    }
}
