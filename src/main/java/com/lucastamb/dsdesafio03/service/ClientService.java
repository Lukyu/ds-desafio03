package com.lucastamb.dsdesafio03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucastamb.dsdesafio03.dto.ClientDTO;
import com.lucastamb.dsdesafio03.entity.Client;
import com.lucastamb.dsdesafio03.exceptions.ClientNotFoundExcpetion;
import com.lucastamb.dsdesafio03.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public void delete(Long id) {
			
		clientRepository.findById(id)
			.orElseThrow(() -> new ClientNotFoundExcpetion("Cliente não encontrado"));
		
		clientRepository.deleteById(id);
		
		return;
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> list(Pageable pageable) {
		
		Page<Client> clientPage = clientRepository.findAll(pageable);
		
		return clientPage.map(client -> {
			return new ClientDTO(client);
		});
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		
		Client client = clientRepository.findById(id)
			.orElseThrow(() -> new ClientNotFoundExcpetion("Cliente não encontrado"));
		
		clientRepository.save(copyDTOToEntity(dto, client));
		
	 	return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		
		Client cliente = new Client(dto);
		
		cliente = clientRepository.save(cliente);
		
		return new ClientDTO(cliente);
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		
		Client cliente = clientRepository.findById(id)
			.orElseThrow(() -> new ClientNotFoundExcpetion("Cliente não encontrado"));
		
		return new ClientDTO(cliente);
	}
	
	private Client copyDTOToEntity(ClientDTO dto, Client enity) {

		enity.setName(dto.getName());
		enity.setCpf(dto.getCpf());
		enity.setIncome(dto.getIncome());
		enity.setBirthDate(dto.getBirthDate());
		enity.setChildren(dto.getChildren());
		
		return enity;
		
	}
}
