package com.nttdata.bootcam.banca.consulta.client.infraestructure;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.nttdata.bootcam.banca.consulta.client.dto.event.BuyProductEvent;
import com.nttdata.bootcam.banca.consulta.client.dto.event.ClientCatalogEvent;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.ClientCreatedEvent;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.ClientCreatedEventCompra;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.Event;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.EventType;
import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.dao.SolicitudCatalogoDAO;
import com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.dao.SolicitudCompraDAO;
import com.nttdata.bootcam.banca.consulta.client.service.ClientMessageService;
import com.nttdata.bootcam.banca.consulta.client.util.JsonUtil;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Value;

@Component
public class ClientEventsService {

	@Autowired
	private KafkaTemplate<String, Event<?>> producer;
	@Autowired
	private ClientMessageService clientMessageService;

	@Value("${catalog-request-topic}")
	private String topicCustomer;

	@Value("${order-request-topic}")
	private String topicCustomerOrder;

	@PostConstruct
	public void init() {
		System.out.println("topicCustomer: " + topicCustomer);
		System.out.println("topicCustomerOrder: " + topicCustomerOrder);
	}

	public void publishCatalogo(ClientCatalogEvent client) {
		ClientCreatedEvent selected = new ClientCreatedEvent();
		selected.setData(client);
		selected.setId(UUID.randomUUID().toString());
		selected.setType(EventType.CATALOGO);
		selected.setDate(new Date());
		selected.setTopico("catalog-request-topic");
		System.out.println("<<ClientCreatedEvent>>" + selected.getData());
		System.out.println("<<ClientCreatedEvent>>" + selected.getId());///////// 736f97af-68f4-4f5a-8c54-ed075e25ef7a
		System.out.println("<<ClientCreatedEvent>>" + selected.getType());///////////// CATALOGO
		System.out.println("<<ClientCreatedEvent>>" + selected.getDate());
		System.out.println("<<ClientCreatedEvent>>" + selected.getTopico());
		SolicitudCatalogoDAO scdao = new SolicitudCatalogoDAO();
		scdao.setDataMensaje(JsonUtil.convertirObjetoAJson(client));
		scdao.setDateMensaje(selected.getDate());
		scdao.setIdCliente(client.getId());
		scdao.setIdMensaje(selected.getId());
		scdao.setTopico(selected.getTopico());
		scdao.setTypeMensaje(selected.getType().name());
		scdao.setId(selected.getId());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getDataMensaje());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getDateMensaje());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getIdCliente());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getIdMensaje());///////// 736f97af-68f4-4f5a-8c54-ed075e25ef7a
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getTopico());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getTypeMensaje());//////////// CATALOGO
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getId());//////// 736f97af-68f4-4f5a-8c54-ed075e25ef7a

		clientMessageService.getIdMensaje(scdao.getTypeMensaje())
				.filter(solicitud -> selected.getId().equals(solicitud.getIdMensaje())).collectList()
				.flatMapMany(filteredSolicitudes -> {
					if (filteredSolicitudes.isEmpty()) {
						return clientMessageService.saveMessageCatalog(scdao).flux();
					} else {
						System.out.println("---MENSAJE YA EXISTE EN EL TOPICO---");
						return Flux.empty();
					}
				}).doOnNext(result -> {
					if (result != null) {
						this.producer.send(topicCustomer, selected);
						System.out.println("---PASANDO LA PUBLICACION---" + result);
					}
				}).subscribe();

	}

	public void publishCompra(BuyProductEvent client) {
		ClientCreatedEventCompra saved = new ClientCreatedEventCompra();
		saved.setData(client);
		saved.setId(UUID.randomUUID().toString());
		saved.setType(EventType.COMPRA);
		saved.setDate(new Date());
		saved.setTopico("order-request-topic");
		System.out.println("<<ClientCreatedEvent>>" + saved.getData());
		System.out.println("<<ClientCreatedEvent>>" + saved.getId());///////// 736f97af-68f4-4f5a-8c54-ed075e25ef7a
		System.out.println("<<ClientCreatedEvent>>" + saved.getType());///////////// COMPRA
		System.out.println("<<ClientCreatedEvent>>" + saved.getDate());
		System.out.println("<<ClientCreatedEvent>>" + saved.getTopico());
		SolicitudCompraDAO scdao = new SolicitudCompraDAO();
		scdao.setDataMensaje(JsonUtil.convertirObjetoAJson(client));
		scdao.setDateMensaje(saved.getDate());
		scdao.setIdCliente(client.getId());
		scdao.setIdMensaje(saved.getId());
		scdao.setTopico(saved.getTopico());
		scdao.setTypeMensaje(saved.getType().name());
		scdao.setId(saved.getId());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getDataMensaje());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getDateMensaje());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getIdCliente());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getIdMensaje());///////// 736f97af-68f4-4f5a-8c54-ed075e25ef7a
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getTopico());
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getTypeMensaje());//////////// CATALOGO
		System.out.println("<<SolicitudCatalogoDAO>>" + scdao.getId());//////// 736f97af-68f4-4f5a-8c54-ed075e25ef7a

		clientMessageService.getIdMensajeCompra(scdao.getTypeMensaje())
				.filter(solici -> saved.getId().equals(solici.getIdMensaje())).collectList()
				.flatMapMany(filteredsoli -> {
					if (filteredsoli.isEmpty()) {
						return clientMessageService.saveMessageCompra(scdao).flux();
					} else {
						System.out.println("- <<getIdMensajeCompra>>--MENSAJE YA EXISTE EN EL TOPICO---");
						return Flux.empty();
					}
				}).doOnNext(result -> {
					if (result != null) {
						this.producer.send(topicCustomerOrder, saved);
						System.out.println("--<<getIdMensajeCompra>>--PASANDO LA PUBLICACION---" + result);
					}
				}).subscribe();

	}
}
