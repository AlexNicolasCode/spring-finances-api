package com.finances.api.domain.usecases.loadAccountsByUserId;

import java.util.UUID;

public record LoadAccountsByUserIdUseCaseOutputDto(
        UUID id,
        String name,
        float balance
) {}
