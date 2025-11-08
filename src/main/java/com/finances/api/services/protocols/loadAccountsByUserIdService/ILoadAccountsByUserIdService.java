package com.finances.api.services.protocols.loadAccountsByUserIdService;

import java.util.List;
import java.util.UUID;

public interface ILoadAccountsByUserIdService {
    List<LoadAccountsByUserIdServiceOutputDto> loadAccountsByUserId(UUID id);
}
