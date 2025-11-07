package com.finances.api.services.protocols.transferValueBetweenAccountService;

import java.util.UUID;

public record TransferValueBetweenAccountServiceInputDto(
    UUID transactionId,
    UUID targetAccountId,
    UUID fromAccountId,
    float value
) {}
