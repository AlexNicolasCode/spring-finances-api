package com.finances.api.infra.repositories;

import com.finances.api.data.protocols.loadTransactionsRepository.LoadTransactionsRepositoryOutputDto;
import com.finances.api.infra.entities.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CrudTransactionRepository extends CrudRepository<TransactionEntity, UUID> {

    @Query("""
        SELECT new com.finances.api.data.protocols.loadTransactionsRepository.LoadTransactionsRepositoryOutputDto(
            t.id,
            a.name,
            t.type,
            CAST(t.value AS string)
        )
        FROM transactions t
        INNER JOIN accounts a ON t.targetAccountId = a.id
        WHERE t.fromAccountId = :accountId
            AND a.name ILIKE %:search%
                OR t.type ILIKE %:search%
        ORDER BY t.createdAt DESC
    """)
    List<LoadTransactionsRepositoryOutputDto> loadByAccountId(
            @Param("accountId") UUID accountId,
            @Param("search") String search
    );
}
