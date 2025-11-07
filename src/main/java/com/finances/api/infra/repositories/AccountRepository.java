package com.finances.api.infra.repositories;

import com.finances.api.data.protocols.checkAccountByIdRepository.ICheckAccountByIdRepository;
import com.finances.api.data.protocols.loadAccountBalanceByIdRepository.ILoadAccountBalanceByIdRepository;
import com.finances.api.data.protocols.loadAccountBalanceByIdRepository.LoadAccountBalanceByIdRepositoryOutputDto;
import com.finances.api.data.protocols.transferValueBetweenAccountRepository.ITransferValueBetweenAccountRepository;
import com.finances.api.data.protocols.transferValueBetweenAccountRepository.TransferValueBetweenAccountRepositoryInputDto;
import com.finances.api.infra.entities.AccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountRepository implements
        ICheckAccountByIdRepository,
        ITransferValueBetweenAccountRepository,
        ILoadAccountBalanceByIdRepository
{
    private final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

    @Autowired
    private CrudAccountRepository repo;

    public boolean checkAccountById(UUID accountId) {
        try {
            return this.repo.existsById(accountId);
        } catch (Exception error) {
            this.logger.error(error.getMessage());
            return false;
        }
    }

    @Transactional
    public void transferValueBetweenAccount(TransferValueBetweenAccountRepositoryInputDto transferDto) throws Exception {
        Optional<AccountEntity> fromAccount = this.repo.findById(transferDto.fromAccountId());
        Optional<AccountEntity> targetAccount = this.repo.findById(transferDto.targetAccountId());
        if (fromAccount.isEmpty() || targetAccount.isEmpty()) {
            throw new Exception("Invalid account");
        }
        float fromAccountBalance = fromAccount.get().getBalance();
        if (transferDto.value() > fromAccountBalance) {
            throw new Exception("Invalid value");
        }
        fromAccount.get().setBalance(
                fromAccount.get().getBalance() - transferDto.value()
        );
        targetAccount.get().setBalance(
                targetAccount.get().getBalance() + transferDto.value()
        );
        this.repo.save(targetAccount.get());
        this.repo.save(fromAccount.get());
    }

    public LoadAccountBalanceByIdRepositoryOutputDto loadAccountBalanceById(UUID accountId) {
        return this.repo.loadAccountBalanceById(accountId);
    }
}
