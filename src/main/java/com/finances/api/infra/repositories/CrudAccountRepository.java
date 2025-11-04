package com.finances.api.infra.repositories;

import com.finances.api.infra.entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CrudAccountRepository extends CrudRepository<AccountEntity, UUID> {
    List<AccountEntity> id(UUID id);
}
