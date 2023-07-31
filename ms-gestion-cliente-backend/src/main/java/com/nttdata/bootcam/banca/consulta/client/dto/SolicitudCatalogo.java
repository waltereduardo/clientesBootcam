package com.nttdata.bootcam.banca.consulta.client.dto;

import lombok.Data;

@Data
public class SolicitudCatalogo {
	private String idMensaje;
	private String typeMensaje;
	private String dataMensaje;
	private String idCliente;
	private String dateMensaje;
	private String topico;
}
