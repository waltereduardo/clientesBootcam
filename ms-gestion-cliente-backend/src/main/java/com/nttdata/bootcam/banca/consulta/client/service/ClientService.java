package com.nttdata.bootcam.banca.consulta.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nttdata.bootcam.banca.consulta.client.repository.ClientRepository;
import com.nttdata.bootcam.banca.consulta.client.repository.dao.ClientDAO;

import io.reactivex.rxjava3.core.Maybe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Flux<ClientDAO>  getClientAll() {
		return clientRepository.findAll();
	};
	
	public Mono<ClientDAO> findById(String id) {
		
		return clientRepository.findById(id);
	}
	/**
	 * Devuelve el tipo de cliente , a partir de su numero de documento
	 * 
	 */
	public Mono<ClientDAO> typeClient(String numberDocument){
		
		return clientRepository.findByNumberDocument(numberDocument);
	}
}
