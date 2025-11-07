package com.finances.api.services.protocols.loadTransactionByIdService;

import java.util.UUID;

public interface ILoadTransactionByIdService {
    LoadTransactionByIdServiceOutputDto loadTransactionById(UUID transactionId) throws Exception;
}
