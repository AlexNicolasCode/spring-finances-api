package com.finances.api.data.protocols.transferValueBetweenAccountRepository;

public interface ITransferValueBetweenAccountRepository {
    void transferValueBetweenAccount(TransferValueBetweenAccountRepositoryInputDto transferDto) throws Exception;
}
