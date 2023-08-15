package com.reba.challenge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "OpenAPI definition",
        version = "v0"
    ),
    servers = {
        @Server(
            url = "https://rebaapp-production.up.railway.app",
            description = "Generated server url"
        )
    }
)
public class OpenApiConfig {
    // Esta clase se utiliza solo para la configuración de OpenAPI,
    // no es necesario agregar más contenido aquí.
}
