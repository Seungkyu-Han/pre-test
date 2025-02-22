package com.seungkyu.test.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig{

    @Bean
    fun openApi(): OpenAPI {

        return OpenAPI()
            .components(Components())
            .info(
                Info().apply {
                    title = "pre-test"
                    description = "사전과제의 Swagger입니다."
                    contact = Contact().apply {
                        name = "한승규"
                        email = "trust@gmail.com"
                        url = "https://github.com/Seungkyu-Han"
                    }
                }
            )
            .servers(
                listOf(
                    Server().apply {
                        url = "http://localhost:11000"
                        description = "개발 서버"
                    }
                )
            )

    }
}