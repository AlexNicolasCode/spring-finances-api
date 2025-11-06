package com.finances.api.data.protocols.loadTransactionsRepository;

import java.util.UUID;

public record LoadTransactionsRepositoryOutputDto(
        UUID id,
        String accountName,
        String type,
        String value
) {}