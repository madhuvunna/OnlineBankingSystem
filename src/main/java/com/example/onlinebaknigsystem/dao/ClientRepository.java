package com.example.onlinebaknigsystem.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import java.awt.print.Pageable;

import org.springframework.data.jpa.domain.Specification;

import com.example.onlinebaknigsystem.dao.ClientRepository;
import com.example.onlinebaknigsystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {

	boolean existsByUsername(String username);

	Optional<Client> findByUsername(String username);

	public Page<Client> findAll(Specification<Client> spec, Pageable pageable);

}
