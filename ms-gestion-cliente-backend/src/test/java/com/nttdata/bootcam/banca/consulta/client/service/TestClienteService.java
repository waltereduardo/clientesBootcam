package com.nttdata.bootcam.banca.consulta.client.service;

import com.nttdata.bootcam.banca.consulta.client.repository.ClientRepository;
import com.nttdata.bootcam.banca.consulta.client.repository.dao.ClientDAO;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * TEST01. Servicio de gestion de los clientes
 * @version  1.0.0
 * @author wrodrigr
 */
public class TestClienteService {

@Mock
private ClientRepository clientRepository;
@InjectMocks
private ClientService clientService;

@BeforeEach
public void setup() {
	MockitoAnnotations.openMocks(this);
}

@Test
public void testGetClientAll() {
	ClientDAO client1 = new ClientDAO();
	client1.setId("1");
	client1.setTypeDocument("DNI");
	client1.setNumberDocument("43455644");
	client1.setNameAll("Jose Luis");

	ClientDAO client2 = new ClientDAO();
	client2.setId("2");
	client2.setTypeDocument("PASAPORTE");
	client2.setNumberDocument("345344");
	client2.setNameAll("Sofia marlene");

	when(clientRepository.findAll()).thenReturn(Flux.just(client1, client2));

	Flux<ClientDAO> result = clientService.getClientAll();

	StepVerifier.create(result).expectNext(client1).expectNext(client2).verifyComplete();
	}

@Test
public void testFindById() {
	String clientId = "1";
	ClientDAO client = new ClientDAO();
	client.setId(clientId);
	client.setTypeDocument("DNI");
	client.setNumberDocument("5677777");
	client.setNameAll("Sofia");

	when(clientRepository.findById(anyString())).thenReturn(Mono.just(client));

	Mono<ClientDAO> result = clientService.findById(clientId);

	StepVerifier.create(result).expectNext(client).verifyComplete();
	}

}
