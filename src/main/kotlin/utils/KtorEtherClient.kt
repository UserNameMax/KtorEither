package utils

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import network.model.Either
import network.model.ErrorResponse

object KtorEtherClient {
    val httpClient = HttpClient(CIO) {
        install(KtorEitherPlugin)
    }

    enum class RestMethod { Get, Post, Delete, Put }

    suspend inline fun <reified T> securityResponse(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {},
        method: RestMethod = RestMethod.Get
    ): Either<ErrorResponse, T> =
        try {
            when (method) {
                RestMethod.Get -> httpClient.get(urlString, block)
                RestMethod.Post -> httpClient.post(urlString, block)
                RestMethod.Delete -> httpClient.delete(urlString, block)
                RestMethod.Put -> httpClient.put(urlString, block)
            }.body()
        } catch (e: Exception) {
            Either.Error(ErrorResponse(code = 0, description = e.message ?: "Error"))
        }
}