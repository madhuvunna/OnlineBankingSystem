package com.example.onlinebaknigsystem.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.onlinebaknigsystem.dto.CreateClientRequest;
import com.example.onlinebaknigsystem.model.Client;

public interface ClientService {

	public List<Client> getAllClinet();

	public Client createClient(CreateClientRequest request);

	public void updateEmail(Long clientId, String oldEmail, String newEmail);

	public void transferMoney(Long fromClientId, Long toClientId, BigDecimal amount);

	public void updatePhone(Long clientId, String oldPhone, String newPhone);

}
