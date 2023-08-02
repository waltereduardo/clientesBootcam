package com.nttdata.bootcam.banca.consulta.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.PedidoCatalogoRepository;
import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.PedidoCompraRepository;
import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.dao.SolicitudCatalogoDAO;
import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.dao.SolicitudCompraDAO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio de mensajeria de los clientes
 * 
 * @author wrodrigr
 * 
 */
@Service
public class ClientMessageService {

	@Autowired
	private PedidoCatalogoRepository pedidoCatalogoRepository;
	@Autowired
	private PedidoCompraRepository pedidoCompraRepository;

	public Mono<SolicitudCatalogoDAO> saveMessageCatalog(SolicitudCatalogoDAO solicitudCatalogo) {
		return pedidoCatalogoRepository.save(solicitudCatalogo).doOnSuccess(result -> {
			System.out.println("GUARDADO EL MENSAJE DEL CATALOGO" + result);
		}).doOnError(error -> {
			System.out.println("ERROR EN EL MENSAJE DE CATALOGO: " + error.getMessage());
		});
	}

	public Flux<SolicitudCatalogoDAO> getIdMensaje(String typeMensaje) {

		return pedidoCatalogoRepository.findByTypeMensaje(typeMensaje);
	}

	public Mono<SolicitudCompraDAO> saveMessageCompra(SolicitudCompraDAO solicitudCatalogo) {
		return pedidoCompraRepository.save(solicitudCatalogo).doOnSuccess(result -> {
			System.out.println("GUARDADO EL MENSAJE DE COMPRA" + result);
		}).doOnError(error -> {
			System.out.println("ERROR EN EL MENSAJE DE COMPRA: " + error.getMessage());
		});
	}

	public Flux<SolicitudCompraDAO> getIdMensajeCompra(String typeMensaje) {

		return pedidoCompraRepository.findByTypeMensaje(typeMensaje);
	}

}
