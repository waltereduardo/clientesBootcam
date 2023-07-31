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
import com.nttdata.bootcam.banca.consulta.client.service.ClientMessageService;
import com.nttdata.bootcam.banca.consulta.client.util.Constantes;
import com.nttdata.bootcam.banca.consulta.client.util.JsonUtil;

import jakarta.annotation.PostConstruct;

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
//		selected.setId(UUID.randomUUID().toString());
		selected.setId("1222d406-3635-4213-8774-b11507f34afd");
		
		selected.setType(EventType.CATALOGO);
		selected.setDate(new Date());
		selected.setTopico("catalog-request-topic");

		SolicitudCatalogoDAO scdao = new SolicitudCatalogoDAO();
		scdao.setDataMensaje(JsonUtil.convertirObjetoAJson(client));
		scdao.setDateMensaje(selected.getDate());
		scdao.setIdCliente(client.getId());
		scdao.setIdMensaje(selected.getId());
		scdao.setTopico(selected.getTopico());
		scdao.setTypeMensaje(selected.getType().name());
		scdao.setId(selected.getId());
		System.out.println("BUSQUEMOS EN LA BD :" + selected.getId());
		clientMessageService.getIdMensaje(selected.getId())
				.filter(rdao -> rdao.getIdMensaje() != null && !rdao.getIdMensaje().isEmpty())
				.switchIfEmpty(clientMessageService.saveMessageCatalog(scdao)).subscribe(result -> {
					if (result != null) {
						this.producer.send(topicCustomer, selected);
						System.out.println("---PASANDO LA PUBLICACION---" + result);
					} else {
						System.out.println("---MENSAJE YA EXISTE EN EL TOPICO---");
					}
//			System.out.println("ttttttttttt:" + rdao.getIdMensaje());
//			if (rdao.getIdMensaje().isEmpty()) {
//				clientMessageService.saveMessageCatalog(scdao).subscribe(result -> {
//					this.producer.send(topicCustomer, selected);
//					System.out.println("---PASANDO LA PUBLICACION---" + result);
//				}, error -> {
//					System.out.println("ERROR" + error.getMessage());
//				});
//
//			} else {
//				System.out.println("---MENSAJE YA EXISTE EN EL TOPICO---");
//			}
				}, error -> {
					System.out.println("ERROR" + error.getMessage());
				});

//		clientMessageService.saveMessageCatalog(scdao)
//		.subscribe(result->{
//			this.producer.send(topicCustomer, selected);
//			System.out.println("---PASANDO LA PUBLICACION---"  + result) ;
//		}, error->{
//			System.out.println("ERROR"  + error.getMessage()) ;
//		} );
//		

	}

	public void publishCompra(BuyProductEvent client) {
		ClientCreatedEventCompra saved = new ClientCreatedEventCompra();
		saved.setData(client);
		saved.setId(UUID.randomUUID().toString());
		saved.setType(EventType.CREATED);
		saved.setDate(new Date());
		saved.setTopico("order-request-topic");
		if (Constantes.MSG_COMPRA.equals(client.getMensaje()))
			this.producer.send(topicCustomerOrder, saved);
	}
}
