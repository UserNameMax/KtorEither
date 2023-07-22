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
import utils.KtorEtherClient

class Api {
    private val client = KtorEtherClient
    suspend fun getPet(): Either<ErrorResponse, Pet> = client.securityResponse("https://petstore.swagger.io/v2/pet/68")
    suspend fun getPets(): Either<ErrorResponse, List<Pet>> =
        client.securityResponse("https://petstore.swagger.io/v2/pet/findByStatus?status=sold")
}