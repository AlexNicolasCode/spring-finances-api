package com.finances.api.services.protocols.loadAccountsByUserIdService;

import java.util.UUID;

public record LoadAccountsByUserIdServiceOutputDto(
        UUID id,
        String name,
        float balance
) {}
