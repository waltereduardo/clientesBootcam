package com.nttdata.bootcam.banca.consulta.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcam.banca.consulta.client.repository.dao.CreditClientDAO;

public interface CredicClientRepository extends ReactiveMongoRepository<CreditClientDAO, String> {

}
