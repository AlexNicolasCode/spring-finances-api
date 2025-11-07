package com.finances.api;

import com.finances.api.data.protocols.amqpProducer.AmqpProducer;
import com.finances.api.data.protocols.checkAccountByIdRepository.ICheckAccountByIdRepository;
import com.finances.api.data.protocols.createTransactionRepository.ICreateTransactionRepository;
import com.finances.api.data.protocols.loadTransactionsRepository.ILoadTransactionsRepository;
import com.finances.api.data.services.AccountService;
import com.finances.api.data.services.AmqpService;
import com.finances.api.data.services.TransactionService;
import com.finances.api.infra.amqp.RabbitMqProducer;
import com.finances.api.infra.repositories.TransactionRepository;
import com.finances.api.services.protocols.checkAccountByIdService.ICheckAccountByIdService;
import com.finances.api.infra.repositories.AccountRepository;
import com.finances.api.services.protocols.createTransactionService.ICreateTransactionService;
import com.finances.api.services.protocols.loadTransactionsService.ILoadTransactionsService;
import com.finances.api.services.protocols.sendTransactionToQueueService.ISendTransactionToQueueService;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ICheckAccountByIdRepository getCheckAccountByIdRepository() {
        return new AccountRepository();
    }

    @Bean
    public ICheckAccountByIdService getCheckAccountById() {
        return new AccountService(this.getCheckAccountByIdRepository());
    }

    @Bean
    public ILoadTransactionsService getLoadTransactionsService() {
        return new TransactionService(this.getCreateTransactionRepository(), this.getLoadTransactionsRepository());
    }

    @Bean
    public ICreateTransactionRepository getCreateTransactionRepository() {
        return new TransactionRepository();
    }

    @Bean
    public ILoadTransactionsRepository getLoadTransactionsRepository() {
        return new TransactionRepository();
    }

    @Bean
    @Primary
    public ICreateTransactionService getCreateTransactionService() {
        return new TransactionService(this.getCreateTransactionRepository(), this.getLoadTransactionsRepository());
    }

    @Bean
    public AmqpProducer getAmqpProducer() {
        return new RabbitMqProducer(new RabbitTemplate(this.connectionFactory()));
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public ISendTransactionToQueueService getSendTransactionToQueueService() {
        return new AmqpService(this.getAmqpProducer());
    }
}
