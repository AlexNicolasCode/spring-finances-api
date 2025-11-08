package com.finances.api.domain.usecases.loadAccountsByUserId;

import java.util.List;
import java.util.UUID;

public interface ILoadAccountsByUserIdUseCase {
    List<LoadAccountsByUserIdUseCaseOutputDto> loadAccountsByUserId(UUID userId);
}
