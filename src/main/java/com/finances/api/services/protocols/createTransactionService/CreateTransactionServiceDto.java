package com.finances.api.services.protocols.createTransactionService;

import java.util.UUID;

public record CreateTransactionServiceDto(
        Double value,
        String type,
        UUID fromAccountId,
        UUID targetAccountId
) {}
