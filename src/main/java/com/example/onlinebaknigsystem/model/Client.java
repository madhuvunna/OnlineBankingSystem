package com.example.onlinebaknigsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long ClientId;

	@Column(name = "name")
	private String Name;

	@Column(name = "date_of_birth")
	private LocalDate dateofBirth;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String passWord;

	@ElementCollection
    @CollectionTable(name = "client_phone_numbers", joinColumns = @JoinColumn(name = "client_id"))
	@Column(name = "client_phone_number")
	private List<String> phoneNumber = new ArrayList<>();

	@ElementCollection
    @CollectionTable(name = "client_emails", joinColumns = @JoinColumn(name = "client_id"))
	@Column(name = "client_emails")
	private List<String> emails = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;

	public Client() {
		super();
	}

	public Client(Long clientId, String name, LocalDate dateofBirth, String userName, String passWord,
			List<String> phoneNumber, List<String> emails, BankAccount bankAccount) {
		super();
		ClientId = clientId;
		Name = name;
		this.dateofBirth = dateofBirth;
		this.userName = userName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
		this.emails = emails;
		this.bankAccount = bankAccount;
	}

	public Long getClientId() {
		return ClientId;
	}

	public void setClientId(Long clientId) {
		ClientId = clientId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public LocalDate getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(LocalDate dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public String toString() {
		return "Client [ClientId=" + ClientId + ", Name=" + Name + ", dateofBirth=" + dateofBirth + ", userName="
				+ userName + ", passWord=" + passWord + ", phoneNumber=" + phoneNumber + ", emails=" + emails
				+ ", bankAccount=" + bankAccount + "]";
	}


	public Client orelseThrow(Object object) {
		return null;
	}
	
}
