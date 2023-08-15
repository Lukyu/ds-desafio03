package com.lucastamb.dsdesafio03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucastamb.dsdesafio03.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
