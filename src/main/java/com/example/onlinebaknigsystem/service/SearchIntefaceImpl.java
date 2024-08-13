package com.example.onlinebaknigsystem.service;

import java.awt.print.Pageable;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.example.onlinebaknigsystem.dao.ClientRepository;
import com.example.onlinebaknigsystem.model.Client;

public class SearchIntefaceImpl {

	@Autowired 
	private ClientRepository clientRepo;

    public Page<Client> searchClients(String Name, String phoneNumber, String emails, LocalDate dateofBirth, Pageable pageable) {
        Specification<Client> spec = Specification.where(null);

        if (Name != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"), Name + "%"));
        }
        if (phoneNumber != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.isMember(phoneNumber, root.get("phone")));
        }
        if (emails != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.isMember(emails, root.get("email")));
        }
        if (dateofBirth != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dob"), dateofBirth));
        }

        return clientRepo.findAll(spec, pageable);
    }

}
