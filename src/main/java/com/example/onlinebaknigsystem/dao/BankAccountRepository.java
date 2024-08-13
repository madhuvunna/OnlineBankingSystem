package com.example.onlinebaknigsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinebaknigsystem.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
