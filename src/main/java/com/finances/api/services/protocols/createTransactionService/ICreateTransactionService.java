package com.finances.api.services.protocols.createTransactionService;

import java.util.UUID;

public interface ICreateTransactionService {
    UUID createTransaction(CreateTransactionServiceDto dto);
}
