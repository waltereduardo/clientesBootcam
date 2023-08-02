package com.nttdata.bootcam.banca.consulta.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcam.banca.consulta.client.repository.dao.ClientDAO;

import reactor.core.publisher.Mono;

public interface ClientRepository extends ReactiveMongoRepository<ClientDAO, String> {

	Mono<ClientDAO> findByNumberDocument(String dni);

}
