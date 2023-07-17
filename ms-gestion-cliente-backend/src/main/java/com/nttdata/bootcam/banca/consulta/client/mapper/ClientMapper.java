package com.nttdata.bootcam.banca.consulta.client.mapper;

import com.nttdata.bootcam.banca.consulta.client.dto.ClientResponse;
import com.nttdata.bootcam.banca.consulta.client.repository.dao.ClientDAO;

public class ClientMapper {

	@SuppressWarnings("unused")
	public ClientResponse fromClient(ClientDAO clientDao) {
		ClientResponse cResponse = new ClientResponse();
		cResponse.setId(clientDao.getId());
		cResponse.setTypeDocument(clientDao.getTypeDocument());
		cResponse.setNumberDocument(clientDao.getNumberDocument());
		cResponse.setTypeClient(clientDao.getTypeClient());
		cResponse.setNameAll(clientDao.getNameAll());
		return cResponse;
	}
}
