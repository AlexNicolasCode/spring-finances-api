package com.finances.api.data.protocols.loadTransactionsRepository;

import java.util.List;
import java.util.UUID;

public interface ILoadTransactionsRepository {
    List<LoadTransactionsRepositoryOutputDto> loadTransactions(int page, int size, UUID accountId);
}
