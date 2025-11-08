package com.finances.api.data.protocols.loadAccountsByUserIdRepository;

import java.util.UUID;

public record LoadAccountsByUserIdRepositoryOutputDto(
        UUID id,
        String name,
        float balance
) {}
