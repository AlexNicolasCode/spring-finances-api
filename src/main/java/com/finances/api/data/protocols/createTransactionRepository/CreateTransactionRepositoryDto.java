package com.finances.api.data.protocols.createTransactionRepository;

import java.util.UUID;

public record CreateTransactionRepositoryDto(
        Double value,
        String type,
        UUID fromAccountId,
        UUID targetAccountId
) {
}
