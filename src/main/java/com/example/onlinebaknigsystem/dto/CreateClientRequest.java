package com.example.onlinebaknigsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreateClientRequest {
	private String name;
    private LocalDate dateOfBirth;
    private String username;
    private String password;
    private List<String> phoneNumber;
    private List<String> emails;
    private BigDecimal initialBalance; 
    private BigDecimal currentBalance;
    private BigDecimal maxBalance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<String> getEmails() {
		return emails;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
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
    
    
}
