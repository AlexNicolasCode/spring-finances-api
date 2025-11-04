package com.finances.api.data.protocols.createTransactionRepository;

import java.util.UUID;

public record CreateTransactionRepositoryDto(
        float value,
        String type,
        UUID fromAccountId,
        UUID targetAccountId
) {
}
