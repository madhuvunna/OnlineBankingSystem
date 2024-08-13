package com.example.onlinebaknigsystem.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bankaccount")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bankAccountId;

	@Column(name = "initial_balance")
	private BigDecimal initialBalance;

	@Column(name = "current_balance")
	private BigDecimal currentBalance;

	@Column(name = "max_balance")
	private BigDecimal maxBalance;

//	@OneToOne
//	@JoinColumn(name = "bank_account_id")
//	private Client client;

	public BankAccount() {
		super();
	}

	public BankAccount(Long bankAccountId, BigDecimal initialBalance, BigDecimal currentBalance,
			BigDecimal maxBalance) {
		super();
		this.bankAccountId = bankAccountId;
		this.initialBalance = initialBalance;
		this.currentBalance = currentBalance;
		this.maxBalance = maxBalance;
	}

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public BigDecimal getMaxBalance() {
		return maxBalance;
	}

	public void setMaxBalance(BigDecimal maxBalance) {
		this.maxBalance = maxBalance;
	}

	@Override
	public String toString() {
		return "BankAccount [bankAccountId=" + bankAccountId + ", initialBalance=" + initialBalance
				+ ", currentBalance=" + currentBalance + ", maxBalance=" + maxBalance + "]";
	}

	public void withdraw(BigDecimal amount) {
		if (currentBalance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
			currentBalance = currentBalance.subtract(amount);
		} else {
			throw new IllegalArgumentException("Insufficient balance");
		}
	}
	
	public void deposit(BigDecimal amount) {
        BigDecimal newBalance = currentBalance.add(amount);
        if (newBalance.compareTo(maxBalance) <= 0) {
            currentBalance = newBalance;
        } else {
            throw new IllegalArgumentException("Exceeds maximum allowed balance");
        }
    }

}
