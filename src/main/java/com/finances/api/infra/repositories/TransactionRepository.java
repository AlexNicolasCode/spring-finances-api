package com.finances.api.infra.repositories;

import com.finances.api.data.protocols.createTransactionRepository.CreateTransactionRepositoryDto;
import com.finances.api.data.protocols.createTransactionRepository.ICreateTransactionRepository;
import com.finances.api.infra.entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionRepository implements ICreateTransactionRepository {

    @Autowired
    private CrudTransactionRepository repo;

    public void createTransaction(CreateTransactionRepositoryDto dto) {
        TransactionEntity entity = new TransactionEntity();
        entity.setType(dto.type());
        entity.setValue(dto.value());
        entity.setFromAccountId(dto.fromAccountId());
        entity.setTargetAccountId(dto.targetAccountId());
        this.repo.save(entity);
    }
}