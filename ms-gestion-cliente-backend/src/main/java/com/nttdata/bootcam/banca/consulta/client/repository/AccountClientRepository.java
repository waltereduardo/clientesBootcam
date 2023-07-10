package com.nttdata.bootcam.banca.consulta.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcam.banca.consulta.client.repository.dao.AccountClientDAO;

import reactor.core.publisher.Mono;


public interface AccountClientRepository extends ReactiveMongoRepository<AccountClientDAO, String> {
	  Mono<AccountClientDAO> findByIdClient(String idCliente);
}
