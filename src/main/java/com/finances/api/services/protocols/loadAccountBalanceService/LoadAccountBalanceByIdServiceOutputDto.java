package com.finances.api.services.protocols.loadAccountBalanceService;

import java.util.UUID;

public record LoadAccountBalanceByIdServiceOutputDto(
        UUID id,
        float balance
) {}
