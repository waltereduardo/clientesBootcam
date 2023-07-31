package com.nttdata.bootcam.banca.consulta.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.PedidoCatalogoRepository;
import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.dao.SolicitudCatalogoDAO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientMessageService {

	@Autowired
	private PedidoCatalogoRepository pedidoCatalogoRepository;

	public Mono<SolicitudCatalogoDAO> saveMessageCatalog(SolicitudCatalogoDAO solicitudCatalogo) {
		return pedidoCatalogoRepository.save(solicitudCatalogo).doOnSuccess(result -> {
			System.out.println("GUARDADO " + result);
		}).doOnError(error -> {
			System.out.println("ERROR: " + error.getMessage());
		});
	}

	public Flux<SolicitudCatalogoDAO> getIdMensaje(String typeMensaje) {

		return pedidoCatalogoRepository.findByTypeMensaje(typeMensaje);
	}
}
