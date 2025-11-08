package com.finances.api.data.protocols.loadAccountsByUserIdRepository;

import java.util.List;
import java.util.UUID;

public interface ILoadAccountsByUserIdRepository {
    List<LoadAccountsByUserIdRepositoryOutputDto> loadAccountsByUserId(UUID userId);
}
