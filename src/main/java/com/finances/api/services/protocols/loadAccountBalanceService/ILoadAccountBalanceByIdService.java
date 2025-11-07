package com.finances.api.services.protocols.loadAccountBalanceService;

import java.util.UUID;

public interface ILoadAccountBalanceByIdService {
    LoadAccountBalanceByIdServiceOutputDto loadAccountBalanceById(UUID transactionId);
}
