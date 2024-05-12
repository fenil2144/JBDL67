package com.example.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Modifying
    @Query("update Transaction t set t.transactionStatusEnum= ?2 where t.transactionId = ?1")
    void updateTransaction(String transactionId, TransactionStatusEnum transactionStatusEnum);
}
