package com.finances.api.domain.usecases.loadTransactions;

import java.util.List;

public interface ILoadTransactionsUseCase {
    List<LoadTransactionsUseCaseOutputDto> loadTransactions(LoadTransactionsUseCaseInputDto dto);
}
