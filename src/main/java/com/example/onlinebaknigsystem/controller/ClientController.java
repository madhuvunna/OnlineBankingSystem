package com.example.onlinebaknigsystem.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebaknigsystem.dto.CreateClientRequest;
import com.example.onlinebaknigsystem.model.Client;
import com.example.onlinebaknigsystem.service.ClientService;


@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/create")
	public ResponseEntity<Client> createClinet(@RequestBody CreateClientRequest request){
		Client client = clientService.createClient(request);
		return new ResponseEntity<> (client,HttpStatus.CREATED) ;
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<Client>> getAllClient(){
		List<Client> client = null;
		client = clientService.getAllClinet();
		return new ResponseEntity<List<Client>>(client,HttpStatus.ACCEPTED);
		}
	@PutMapping("/{clientId}/phones")
    public ResponseEntity<Client> updatePhone(@PathVariable Long clientId, @RequestParam String oldPhone, @RequestParam String newPhone) {
        clientService.updatePhone(clientId, oldPhone, newPhone);
        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/{clientId}/emails")
    public ResponseEntity<Client> updateEmail(@PathVariable Long clientId, @RequestParam String oldEmail, @RequestParam String newEmail) {
        clientService.updateEmail(clientId, oldEmail, newEmail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Client> transferMoney(@RequestParam Long fromClientId, @RequestParam Long toClientId, @RequestParam BigDecimal amount) {
        clientService.transferMoney(fromClientId, toClientId, amount);
        return ResponseEntity.ok().build();
    }
	
    }
