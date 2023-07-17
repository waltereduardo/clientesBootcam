package com.nttdata.bootcam.banca.consulta.client.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.nttdata.bootcam.banca.consulta.client.repository.ClientRepository;
import com.nttdata.bootcam.banca.consulta.client.repository.dao.ClientDAO;
import org.springframework.http.MediaType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HandlerClient {

	@Autowired
	private ClientRepository clientRepository;

	public Mono<ServerResponse> getClienteAll(ServerRequest request) {
		Flux<ClientDAO> clientStream = clientRepository.findAll();
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(clientStream, ClientDAO.class);
	}

	public Mono<ServerResponse> findClientById(ServerRequest request) {
		int clientId = Integer.valueOf(request.pathVariable("input"));
		Mono<ClientDAO> clientStream = clientRepository.findById(String.valueOf(clientId));
		return ServerResponse.ok().body(clientStream, ClientDAO.class);
	}

	public Mono<ServerResponse> saveClient(ServerRequest request) {
		Mono<ClientDAO> clientMono = request.bodyToMono(ClientDAO.class);
		Mono<String> saveResponse = clientMono.map(dto -> dto.getId() + ":" + dto.getNameAll()+":"+dto.getNumberDocument()+":"+dto.getTypeDocument()+":"+dto.getTypeClient());
		return ServerResponse.ok().body(saveResponse, String.class);
	}

}
