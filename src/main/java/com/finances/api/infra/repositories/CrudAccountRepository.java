package com.finances.api.infra.repositories;

import com.finances.api.data.protocols.loadAccountBalanceByIdRepository.LoadAccountBalanceByIdRepositoryOutputDto;
import com.finances.api.data.protocols.loadAccountsByUserIdRepository.LoadAccountsByUserIdRepositoryOutputDto;
import com.finances.api.data.protocols.loadTransactionsRepository.LoadTransactionsRepositoryOutputDto;
import com.finances.api.infra.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CrudAccountRepository extends CrudRepository<AccountEntity, UUID> {
    @Query("""
        SELECT new com.finances.api.data.protocols.loadAccountBalanceByIdRepository.LoadAccountBalanceByIdRepositoryOutputDto(
            a.id,
            a.balance
        )
        FROM accounts a
            WHERE a.id = :accountId
    """)
    LoadAccountBalanceByIdRepositoryOutputDto loadAccountBalanceById(
            @Param("accountId") UUID accountId
    );

    @Query("""
        SELECT new com.finances.api.data.protocols.loadAccountsByUserIdRepository.LoadAccountsByUserIdRepositoryOutputDto(
            a.id,
            a.name,
            a.balance
        )
        FROM accounts a
            WHERE a.userId = :userId
    """)
    List<LoadAccountsByUserIdRepositoryOutputDto> loadAccountsByUserId(
            @Param("userId") UUID userId
    );
}
