package network

import network.model.Either
import network.model.ErrorResponse
import utils.KtorEitherPlugin
import network.dto.Pet
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*

class Api {
    private val client = HttpClient(CIO) {
        install(KtorEitherPlugin)
    }

    suspend fun getPet(): Either<ErrorResponse, Pet> = securityResponse("https://petstore.swagger.io/v2/pet/68")

    suspend fun getPets(): Either<ErrorResponse, List<Pet>> =
        securityResponse("https://petstore.swagger.io/v2/pet/findByStatus?status=sold")

    private suspend inline fun <reified T> securityResponse(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): Either<ErrorResponse, T> =
        try {
            client.get(urlString, block).body()
        } catch (e: Exception) {
            Either.Error(ErrorResponse(code = 0, description = e.message ?: "Error"))
        }

}