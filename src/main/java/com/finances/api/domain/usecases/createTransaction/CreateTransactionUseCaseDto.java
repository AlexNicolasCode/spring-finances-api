package com.finances.api.domain.usecases.createTransaction;

import com.finances.api.domain.models.TransactionTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CreateTransactionUseCaseDto(
        @NotNull(message = "Transaction value cannot be null")
        @Positive(message = "Transaction value must be greater than 0")
        float value,

        @NotNull(message = "Transaction type cannot be null")
        TransactionTypeEnum type,

        @NotNull(message = "From account ID cannot be null")
        UUID fromAccountId,

        @NotNull(message = "Target account ID cannot be null")
        UUID targetAccountId
) {}