package com.nttdata.bootcam.banca.consulta.client.infraestructure;

import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.consulta.client.dto.event.BuyProductEvent;
import com.nttdata.bootcam.banca.consulta.client.dto.event.ClientCatalogEvent;
import com.nttdata.bootcam.banca.consulta.client.util.Constantes;

@Service
public class ClientServiceKafka {

	private final ClientEventsService clientEventsService;

	public ClientServiceKafka(ClientEventsService clientEventsService) {
		super();
		this.clientEventsService = clientEventsService;
	}

	public ClientCatalogEvent saveCatalogo(ClientCatalogEvent clientCatalogEvent) {
		System.out.println(" <<saveCatalogo>> El cliente solicita el catalogo" + clientCatalogEvent);
		if (Constantes.MSG_CATALOGO.equals(clientCatalogEvent.getMensaje())) {
			this.clientEventsService.publishCatalogo(clientCatalogEvent);
		} else {
			System.out.println("<<saveCatalogo>> Mensaje incorrecto");
			ClientCatalogEvent clientCatalogEventE = new ClientCatalogEvent();
			clientCatalogEventE.setMensaje("Sin datos. Mensaje incorrecto");
			clientCatalogEvent = clientCatalogEventE;
		}
		return clientCatalogEvent;
	}

	public BuyProductEvent saveOrdenCompra(BuyProductEvent buyProductEvent) {
		System.out.println("<<saveOrdenCompra>> Received la orden de compra" + buyProductEvent);
		if (Constantes.MSG_COMPRA.equals(buyProductEvent.getMensaje())) {
			this.clientEventsService.publishCompra(buyProductEvent);
		} else {
			System.out.println("<<saveOrdenCompra>> Mensaje incorrecto");
			BuyProductEvent clientCompraEventE = new BuyProductEvent();
			clientCompraEventE.setMensaje("Sin datos. Mensaje incorrecto");
			buyProductEvent = clientCompraEventE;
		}

		return buyProductEvent;

	}
}
