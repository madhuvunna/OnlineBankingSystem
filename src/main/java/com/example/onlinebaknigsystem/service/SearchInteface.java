package com.example.onlinebaknigsystem.service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.example.onlinebaknigsystem.model.Client;

public interface SearchInteface {
	public Page<Client> findAll(Specification<Client> spec, Pageable pageable);

	
}
