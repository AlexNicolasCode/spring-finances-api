package com.finances.api.infra.repositories;

import com.finances.api.data.protocols.createTransactionRepository.CreateTransactionRepositoryDto;
import com.finances.api.data.protocols.createTransactionRepository.ICreateTransactionRepository;
import com.finances.api.data.protocols.loadTransactionByIdRepository.ILoadTransactionByIdRepository;
import com.finances.api.data.protocols.loadTransactionByIdRepository.LoadTransactionByIdRepositoryOutputDto;
import com.finances.api.data.protocols.loadTransactionsRepository.ILoadTransactionsRepository;
import com.finances.api.data.protocols.loadTransactionsRepository.LoadTransactionsRepositoryOutputDto;
import com.finances.api.infra.entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionRepository implements ICreateTransactionRepository, ILoadTransactionsRepository, ILoadTransactionByIdRepository {

    @Autowired
    private CrudTransactionRepository repo;

    public UUID createTransaction(CreateTransactionRepositoryDto dto) {
        TransactionEntity entity = new TransactionEntity();
        entity.setType(dto.type());
        entity.setValue(dto.value());
        entity.setFromAccountId(dto.fromAccountId());
        entity.setTargetAccountId(dto.targetAccountId());
        TransactionEntity storagedEntity = this.repo.save(entity);
        return storagedEntity.getId();
    }

    public List<LoadTransactionsRepositoryOutputDto> loadTransactions(
            int page,
            int size,
            UUID accountId,
            String search
    ) {
        return this.repo.loadByAccountId(accountId, search);
    }

    public LoadTransactionByIdRepositoryOutputDto loadTransactionById(UUID transactionId) throws Exception {
        Optional<TransactionEntity> transaction = this.repo.findById(transactionId);
        if (transaction.isEmpty()) {
            throw new Exception("Transaction not found");
        }
        return new LoadTransactionByIdRepositoryOutputDto(
                transaction.get().getId(),
                transaction.get().getType(),
                transaction.get().getStatus(),
                transaction.get().getValue(),
                transaction.get().getFromAccountId(),
                transaction.get().getTargetAccountId()
        );
    }
}