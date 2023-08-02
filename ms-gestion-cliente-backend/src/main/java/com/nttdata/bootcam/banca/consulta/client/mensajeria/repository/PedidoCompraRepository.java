package com.nttdata.bootcam.banca.consulta.client.mensajeria.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.dao.SolicitudCompraDAO;

import reactor.core.publisher.Flux;

public interface PedidoCompraRepository extends ReactiveMongoRepository<SolicitudCompraDAO, String> {
	Flux<SolicitudCompraDAO> findByTypeMensaje(String typeMensaje);
}
