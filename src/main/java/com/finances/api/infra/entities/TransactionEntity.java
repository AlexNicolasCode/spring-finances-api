package com.finances.api.infra.entities;

import com.finances.api.domain.models.TransactionStatusEnum;
import com.finances.api.domain.models.TransactionTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "transactions")
@SQLRestriction("deleted_at IS NULL")
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;

    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum status = TransactionStatusEnum.PROCESSING;

    private float value;

    @Column(name = "from_account_id")
    private UUID fromAccountId;

    @Column(name = "target_account_id")
    private UUID targetAccountId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;
}
