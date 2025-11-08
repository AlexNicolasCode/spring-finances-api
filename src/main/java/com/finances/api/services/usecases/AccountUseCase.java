package com.finances.api.services.usecases;

import com.finances.api.domain.usecases.loadAccountsByUserId.ILoadAccountsByUserIdUseCase;
import com.finances.api.domain.usecases.loadAccountsByUserId.LoadAccountsByUserIdUseCaseOutputDto;
import com.finances.api.services.protocols.loadAccountsByUserIdService.ILoadAccountsByUserIdService;
import com.finances.api.services.protocols.loadAccountsByUserIdService.LoadAccountsByUserIdServiceOutputDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountUseCase implements ILoadAccountsByUserIdUseCase {
    private final ILoadAccountsByUserIdService loadAccountsByUserIdService;

    public AccountUseCase(
            ILoadAccountsByUserIdService loadAccountsByUserIdService
    ) {
        this.loadAccountsByUserIdService = loadAccountsByUserIdService;
    }

    public List<LoadAccountsByUserIdUseCaseOutputDto> loadAccountsByUserId(UUID userId) {
        List<LoadAccountsByUserIdServiceOutputDto> accounts = this.loadAccountsByUserIdService.loadAccountsByUserId(userId);
        List<LoadAccountsByUserIdUseCaseOutputDto> response = new ArrayList<>();
        for (LoadAccountsByUserIdServiceOutputDto account : accounts) {
            response.add(
                    new LoadAccountsByUserIdUseCaseOutputDto(
                            account.id(),
                            account.name(),
                            account.balance()
                    )
            );
        }
        return response;
    }
}
