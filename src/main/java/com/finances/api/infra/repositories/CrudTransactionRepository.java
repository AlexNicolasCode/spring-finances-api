package com.finances.api.infra.repositories;

import com.finances.api.infra.entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudTransactionRepository extends CrudRepository<TransactionEntity, Long> {}
