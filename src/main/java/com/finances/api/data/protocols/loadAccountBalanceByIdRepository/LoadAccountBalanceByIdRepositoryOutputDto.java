package com.finances.api.data.protocols.loadAccountBalanceByIdRepository;

import java.util.UUID;

public record LoadAccountBalanceByIdRepositoryOutputDto(
        UUID id,
        float balance
) {}
