package com.nttdata.bootcam.banca.consulta.client.rest;

import org.apache.catalina.util.ToStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcam.banca.consulta.client.dto.AccountClient;
import com.nttdata.bootcam.banca.consulta.client.dto.AccountClientResponse;
import com.nttdata.bootcam.banca.consulta.client.dto.ClientCreateProduc;
import com.nttdata.bootcam.banca.consulta.client.dto.ClientProductResponse;
import com.nttdata.bootcam.banca.consulta.client.dto.ClientResponse;
import com.nttdata.bootcam.banca.consulta.client.dto.Producto;
import com.nttdata.bootcam.banca.consulta.client.repository.AccountClientRepository;
import com.nttdata.bootcam.banca.consulta.client.repository.ClientProductRepository;
import com.nttdata.bootcam.banca.consulta.client.repository.ClientRepository;
import com.nttdata.bootcam.banca.consulta.client.repository.dao.AccountClientDAO;
import com.nttdata.bootcam.banca.consulta.client.repository.dao.ClientDAO;
import com.nttdata.bootcam.banca.consulta.client.repository.dao.ClientProductDAO;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientResource {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ClientProductRepository clientProductRepository;
	@Autowired
	private AccountClientRepository accountClientRepository;

	private final WebClient webClient;

	// 1. start of CRUD
	@GetMapping
	public Flux getClienteAll(ClientResponse clientResponse) {
		return clientRepository.findAll().map(this::fromClient);
	}

	@GetMapping("/{id}")
	public Mono<ClientResponse> findClientById(@PathVariable String id) {
		return clientRepository.findById(id).map(this::fromClient);
	}

	@PostMapping
	public Mono<ClientResponse> createClient(@RequestBody ClientResponse clientResponse) {
		return clientRepository.save(this.fromClientResponseToClienteDao(clientResponse)).map(this::fromClient);
	}

	@PutMapping
	public Mono<ClientDAO> modifyClient(@RequestBody ClientDAO clientDAO) {
		return clientRepository.findById(clientDAO.getId()).flatMap(cl -> {
			cl.setId(clientDAO.getId());
			cl.setTypeDocument(clientDAO.getTypeDocument());
			cl.setNumberDocument(clientDAO.getNumberDocument());
			cl.setNameAll(clientDAO.getNameAll());
			return clientRepository.save(cl);
		});
	}
// end of CRUD

// 2. start register product by client

	@PostMapping("/clipro")
	public Flux<Producto> createClientProduct(@RequestBody ClientCreateProduc clientCreateProduc) {
		// 2.1 start validation
		System.out.println("--------- createClientProduct  ---- " + clientCreateProduc.getId() + " /// "
				+ clientCreateProduc.getIdClient());
		return webClient.get().uri("/product").retrieve().bodyToFlux(Producto.class).doOnNext(api -> {
			clientRepository.findById(clientCreateProduc.getId()).map(this::fromClient).subscribe(cliente -> {
				// ---- Verify client people " --------
				String typeClient = cliente.getTypeClient();
				if (typeClient != null) {
					if (cliente.getTypeClient().compareTo("1") == 0) {
						// ------- IS UNIQUE CLIENT--------------
						accountClientRepository.findByIdClient(cliente.getId())
								.map(this::fromAccountClientDAOToAccountClient).subscribe(cli -> {
									if (api.getTypeProduct() != null && api.getTypeProduct().compareTo("1") == 0
											&& api.getDetTypeProduct().compareTo("1") == 0) {
										// ---- One account
										// ------Register the product
										if (StringUtils.isEmpty(cli.getTypeProduct())) {
											AccountClientResponse acr = new AccountClientResponse();
											acr.setIdClient(cliente.getId());
											acr.setIdProduct(api.getId());
											acr.setTypeProduct("1");// AHORRO
											Mono<AccountClientDAO> savedAccount = accountClientRepository
													.save(this.fromAccountClientResponseToAccountClientDAO(acr));
											savedAccount.block();
										}

									} else if (api.getTypeProduct() != null && api.getTypeProduct().compareTo("1") == 0
											&& api.getDetTypeProduct().compareTo("2") == 0) {
										// ---- One account
										// ------Register the product
										if (StringUtils.isEmpty(cli.getTypeProduct())) {
											AccountClientResponse acr = new AccountClientResponse();
											acr.setIdClient(cliente.getId());
											acr.setIdProduct(api.getId());
											acr.setTypeProduct("2");// CUENTA CORRIENTE
											Mono<AccountClientDAO> savedAccount = accountClientRepository
													.save(this.fromAccountClientResponseToAccountClientDAO(acr));
											savedAccount.block();
										}

									} else if (api.getTypeProduct() != null && api.getTypeProduct().compareTo("1") == 0
											&& api.getDetTypeProduct().compareTo("3") == 0) {
										// ---- savings account
										// ------Register the product
										// Many account
											AccountClientResponse acr = new AccountClientResponse();
											acr.setIdClient(cliente.getId());
											acr.setIdProduct(api.getId());
											acr.setTypeProduct("3");// PLAZO FIJO
											Mono<AccountClientDAO> savedAccount = accountClientRepository
													.save(this.fromAccountClientResponseToAccountClientDAO(acr));
											savedAccount.block();
										

									}else {
										System.out.println("----------------other-----------------");
									}

								});

					} else if (cliente.getTypeClient().compareTo("2") == 0) {
						// --------- IS BUSINESS CLIENT ----------
						accountClientRepository.findByIdClient(cliente.getId())
						.map(this::fromAccountClientDAOToAccountClient).subscribe(cli -> {
							if (api.getTypeProduct() != null && api.getTypeProduct().compareTo("1") == 0
									&& api.getDetTypeProduct().compareTo("1") == 0) {
								// ------Troungh exception
								if (StringUtils.isEmpty(cli.getTypeProduct())) {
									System.out.println("--USTED NO CALIFICA PARA UNA CUENTA DE AHORRO --");
									throw new RuntimeException("--USTED NO CALIFICA PARA UNA CUENTA DE AHORRO --");
								}

							} else if (api.getTypeProduct() != null && api.getTypeProduct().compareTo("1") == 0
									&& api.getDetTypeProduct().compareTo("2") == 0) {
								// ------Register the product
								// Many account
									AccountClientResponse acr = new AccountClientResponse();
									acr.setIdClient(cliente.getId());
									acr.setIdProduct(api.getId());
									acr.setTypeProduct("2");// CUENTA CORRIENTE
									Mono<AccountClientDAO> savedAccount = accountClientRepository
											.save(this.fromAccountClientResponseToAccountClientDAO(acr));
									savedAccount.block();

							} else if (api.getTypeProduct() != null && api.getTypeProduct().compareTo("1") == 0
									&& api.getDetTypeProduct().compareTo("3") == 0) {
								//------Troungh exception
								System.out.println("--USTED NO CALIFICA PARA PLAZO FIJO --");							
							}else {
								System.out.println("----------------other-----------------");
							}

						});
						
					}
				}
			});

		});

	}

// end register product by client	
	private ClientResponse fromClient(ClientDAO clientDao) {
		ClientResponse cResponse = new ClientResponse();
		cResponse.setId(clientDao.getId());
		cResponse.setTypeDocument(clientDao.getTypeDocument());
		cResponse.setNumberDocument(clientDao.getNumberDocument());
		cResponse.setTypeClient(clientDao.getTypeClient());
		cResponse.setNameAll(clientDao.getNameAll());

		return cResponse;
	}

	private ClientDAO fromClientResponseToClienteDao(ClientResponse clientResponse) {
		ClientDAO cDao = new ClientDAO();
		cDao.setTypeDocument(clientResponse.getTypeDocument());
		cDao.setNumberDocument(clientResponse.getNumberDocument());
		cDao.setNameAll(clientResponse.getNameAll());
		return cDao;
	}

	private ClientProductDAO fromClientProdutResponseToClienteProductDAO(ClientProductResponse clientProductResponse) {
		ClientProductDAO cResponseDao = new ClientProductDAO();
		cResponseDao.setIdclient(clientProductResponse.getIdclient());
		cResponseDao.setIdproduct(clientProductResponse.getIdproduct());
		cResponseDao.setState(clientProductResponse.getState());
		return cResponseDao;
	}

	private ClientProductResponse fromClientProdutDaoToClienProducResponse(ClientProductDAO clientProductDAO) {
		ClientProductResponse cResponse = new ClientProductResponse();
		cResponse.setIdclient(clientProductDAO.getIdclient());
		cResponse.setIdproduct(clientProductDAO.getIdproduct());
		cResponse.setState(clientProductDAO.getState());
		return cResponse;
	}

	private AccountClient fromAccountClientDAOToAccountClient(AccountClientDAO accountClientDAO) {
		AccountClient acli = new AccountClient();
		acli.setId(accountClientDAO.getId());
		acli.setIdClient(accountClientDAO.getIdClient());
		acli.setIdProduct(accountClientDAO.getIdProduct());
		acli.setTypeProduct(accountClientDAO.getTypeProduct());
		return acli;
	}

	private AccountClientDAO fromAccountClientResponseToAccountClientDAO(AccountClientResponse accountClientResponse) {
		AccountClientDAO acli = new AccountClientDAO();
		int valorDado = (int) Math.floor(Math.random() * 10 + 1);
		acli.setId(String.valueOf(valorDado));
		acli.setIdClient(accountClientResponse.getIdClient());
		acli.setIdProduct(accountClientResponse.getIdProduct());
		acli.setTypeProduct(accountClientResponse.getTypeProduct());
		System.out.println("EN EL DAOOOO " + acli);
		return acli;
	}

}
