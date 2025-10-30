package com.finances.api.services.protocols.checkAccountByIdService;

import java.util.UUID;

public interface ICheckAccountByIdService {
    boolean checkAccountById(UUID accountId);
}