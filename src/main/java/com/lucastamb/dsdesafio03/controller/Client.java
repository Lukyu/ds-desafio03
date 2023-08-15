package com.lucastamb.dsdesafio03.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucastamb.dsdesafio03.dto.ClientDTO;
import com.lucastamb.dsdesafio03.service.ClientService;

import jakarta.validation.Valid;

@RestController
public class Client {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value = "/clients")
	private ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
		
		Page<ClientDTO> page = clientService.list(pageable);
		
		return ResponseEntity.ok().body(page);
	}

	@GetMapping(value = "/clients/{id}")
	private ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		
		ClientDTO dto = clientService.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/clients/{id}")
	private ResponseEntity<Void> delete(@PathVariable Long id) {
		clientService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/clients/{id}")
	private ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody @Valid ClientDTO dto) {
		
		ClientDTO updatedDTO = clientService.update(id, dto);
		
		return ResponseEntity.ok().body(updatedDTO);
	}
	
	@PostMapping(value = "/clients")
	private ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {
		
		ClientDTO created = clientService.insert(dto);
		
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
		
		return ResponseEntity.created(uri).body(created);
	}
}
