package com.example.onlinebaknigsystem.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.onlinebaknigsystem.dao.ClientRepository;
import com.example.onlinebaknigsystem.dto.CreateClientRequest;
import com.example.onlinebaknigsystem.model.BankAccount;
import com.example.onlinebaknigsystem.model.Client;

@Service
public class ClietServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Client> getAllClinet() {
		return clientRepo.findAll();
	}

	public Client createClient(CreateClientRequest request) {
		if (clientRepo.findByUsername(request.getUsername()) != null) {
			throw new IllegalArgumentException("Username already taken");
		}

		Client client = new Client();
		client.setUserName(request.getUsername());
		client.setPassWord(passwordEncoder.encode(request.getPassword()));
		client.setName(request.getName());
		client.setDateofBirth(request.getDateOfBirth());
		client.setPhoneNumber(new ArrayList<>(request.getPhoneNumber()));
		client.setEmails(new ArrayList<>(request.getEmails()));

		BankAccount account = new BankAccount();
		account.setInitialBalance(request.getInitialBalance());
		account.setCurrentBalance(request.getCurrentBalance());
		account.setMaxBalance(request.getMaxBalance().multiply(BigDecimal.valueOf(3.07)));

		client.setBankAccount(account);

		return clientRepo.save(client);
	}

//	@Override
//	public Optional<Client> findByuserName(String username) {
//		return clientRepo.findByuserName(username);
//	}

	public void updatePhone(Long clientId, String oldPhone, String newPhone) {
		Client client = getClient(clientId);
		client.getPhoneNumber().remove(oldPhone);
		client.getPhoneNumber().add(newPhone);
		clientRepo.save(client);
	}

	public void updateEmail(Long clientId, String oldEmail, String newEmail) {
		Client client = getClient(clientId);
		client.getEmails().remove(oldEmail);
		client.getEmails().add(newEmail);
		clientRepo.save(client);
	}

	private Client getClient(Long clientId) {
		return clientRepo.findById(clientId).orElseThrow(() -> new IllegalArgumentException("Client not found"));
	}

	public void transferMoney(Long fromClientId, Long toClientId, BigDecimal amount) {
		Client fromClient = getClient(fromClientId);
		Client toClient = getClient(toClientId);

		synchronized (this) {
			fromClient.getBankAccount().withdraw(amount);
			toClient.getBankAccount().deposit(amount);
		}

	}

	@Scheduled(fixedRate = 60000)
	public void increaseBalance() {
		List<Client> clients = clientRepo.findAll();

		for (Client client : clients) {
			BankAccount account = client.getBankAccount();
			BigDecimal newBalance = account.getCurrentBalance().multiply(BigDecimal.valueOf(1.05));
			if (newBalance.compareTo(account.getMaxBalance()) <= 0) {
				account.setCurrentBalance(newBalance);
			}
		}
		clientRepo.saveAll(clients);
	}

}
