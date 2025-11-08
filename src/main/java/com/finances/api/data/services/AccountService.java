package com.finances.api.data.services;

import com.finances.api.data.protocols.checkAccountByIdRepository.ICheckAccountByIdRepository;
import com.finances.api.data.protocols.loadAccountBalanceByIdRepository.ILoadAccountBalanceByIdRepository;
import com.finances.api.data.protocols.loadAccountBalanceByIdRepository.LoadAccountBalanceByIdRepositoryOutputDto;
import com.finances.api.data.protocols.loadAccountsByUserIdRepository.ILoadAccountsByUserIdRepository;
import com.finances.api.data.protocols.loadAccountsByUserIdRepository.LoadAccountsByUserIdRepositoryOutputDto;
import com.finances.api.data.protocols.transferValueBetweenAccountRepository.ITransferValueBetweenAccountRepository;
import com.finances.api.data.protocols.transferValueBetweenAccountRepository.TransferValueBetweenAccountRepositoryInputDto;
import com.finances.api.services.protocols.checkAccountByIdService.ICheckAccountByIdService;
import com.finances.api.services.protocols.loadAccountBalanceService.ILoadAccountBalanceByIdService;
import com.finances.api.services.protocols.loadAccountBalanceService.LoadAccountBalanceByIdServiceOutputDto;
import com.finances.api.services.protocols.loadAccountsByUserIdService.ILoadAccountsByUserIdService;
import com.finances.api.services.protocols.loadAccountsByUserIdService.LoadAccountsByUserIdServiceOutputDto;
import com.finances.api.services.protocols.transferValueBetweenAccountService.ITransferValueBetweenAccountService;
import com.finances.api.services.protocols.transferValueBetweenAccountService.TransferValueBetweenAccountServiceInputDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService implements
        ICheckAccountByIdService,
        ITransferValueBetweenAccountService,
        ILoadAccountBalanceByIdService,
        ILoadAccountsByUserIdService
{
    private final ICheckAccountByIdRepository checkAccountByIdRepository;
    private final ITransferValueBetweenAccountRepository transferValueBetweenAccountRepository;
    private final ILoadAccountBalanceByIdRepository loadAccountBalanceByIdRepository;
    private final ILoadAccountsByUserIdRepository loadAccountsByUserIdRepository;

    public AccountService(
            ICheckAccountByIdRepository checkAccountByIdRepository,
            ITransferValueBetweenAccountRepository transferValueBetweenAccountRepository,
            ILoadAccountBalanceByIdRepository loadAccountBalanceByIdRepository,
            ILoadAccountsByUserIdRepository loadAccountsByUserIdRepository
    ) {
        this.checkAccountByIdRepository = checkAccountByIdRepository;
        this.transferValueBetweenAccountRepository = transferValueBetweenAccountRepository;
        this.loadAccountBalanceByIdRepository = loadAccountBalanceByIdRepository;
        this.loadAccountsByUserIdRepository = loadAccountsByUserIdRepository;
    }


    public boolean checkAccountById(UUID accountId) {
        return this.checkAccountByIdRepository.checkAccountById(accountId);
    }

    public boolean transferValueBetweenAccount(TransferValueBetweenAccountServiceInputDto transferDto) {
        try {
            this.transferValueBetweenAccountRepository.transferValueBetweenAccount(
                    new TransferValueBetweenAccountRepositoryInputDto(
                            transferDto.transactionId(),
                            transferDto.targetAccountId(),
                            transferDto.fromAccountId(),
                            transferDto.value()
                    )
            );
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public LoadAccountBalanceByIdServiceOutputDto loadAccountBalanceById(UUID accountId) {
        LoadAccountBalanceByIdRepositoryOutputDto accountBalance = this.loadAccountBalanceByIdRepository.loadAccountBalanceById(accountId);
        return new LoadAccountBalanceByIdServiceOutputDto(
                accountBalance.id(),
                accountBalance.balance()
        );
    }

    public List<LoadAccountsByUserIdServiceOutputDto> loadAccountsByUserId(UUID userId) {
        List<LoadAccountsByUserIdRepositoryOutputDto> accounts = this.loadAccountsByUserIdRepository.loadAccountsByUserId(userId);
        List<LoadAccountsByUserIdServiceOutputDto> response = new ArrayList<>();
        for (LoadAccountsByUserIdRepositoryOutputDto account: accounts) {
            response.add(
                    new LoadAccountsByUserIdServiceOutputDto(
                            account.id(),
                            account.name(),
                            account.balance()
                    )
            );
        }
        return response;
    }
}
