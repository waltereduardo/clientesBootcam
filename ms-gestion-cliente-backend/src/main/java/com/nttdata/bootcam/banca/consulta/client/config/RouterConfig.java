package com.nttdata.bootcam.banca.consulta.client.config;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcam.banca.consulta.client.dto.ClientResponse;
import com.nttdata.bootcam.banca.consulta.client.handler.HandlerClient;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;


@Configuration
public class RouterConfig {

	@Autowired
	private HandlerClient handlerClient;

	@RouterOperations({ 
		@RouterOperation(path = "/router/client", 
				produces = {
							MediaType.APPLICATION_JSON_VALUE }, 
							method = RequestMethod.GET, 
							beanClass = HandlerClient.class, 
							beanMethod = "getClienteAll", 
							operation = @Operation(
									operationId = "getClienteAll", 
									responses = {
												@ApiResponse(
														responseCode = "200", 
														description = "successful operation", 
														content = @Content(
																schema = @Schema(
																		implementation = ClientResponse.class)
																)
														) 
												}
									)
		),
        @RouterOperation(
                path = "/router/client/{input}",
                produces = {
                        MediaType.APPLICATION_JSON_VALUE
                },
                method = RequestMethod.GET,
                beanClass = HandlerClient.class,
                beanMethod = "findClient",
                operation = @Operation(
                        operationId = "findClient",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "successful operation",
                                        content = @Content(schema = @Schema(
                                                implementation = ClientResponse.class
                                        ))
                                ),
                                @ApiResponse(responseCode = "404", description = "client not found with" +
                                        " given id")
                        },
                        parameters = {
                                @Parameter(in = ParameterIn.PATH, name = "input")
                        }

                )

        ),
        @RouterOperation(
                path = "/router/client",
                produces = {
                        MediaType.APPLICATION_JSON_VALUE
                },
                method = RequestMethod.POST,
                beanClass = HandlerClient.class,
                beanMethod = "saveClient",
                operation = @Operation(
                        operationId = "saveClient",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "successful operation",
                                        content = @Content(schema = @Schema(
                                                implementation = String.class
                                        ))
                                )
                        },
                        requestBody = @RequestBody(
                                content = @Content(schema = @Schema(
                                        implementation = ClientResponse.class
                                ))
                        )

                )


        )
		
		})
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()
				.GET("", handlerClient::getClienteAll)
				.GET("/router/client/{input}", handlerClient::findClientById)
				.POST("/router/client", handlerClient::saveClient)
				.build();
	}

}
