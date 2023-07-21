import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class Api {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation)
        install(KtorEitherPlugin)
    }

    suspend fun get(): Either<String, Pet> {
        val response = client.get("https://petstore.swagger.io/v2/pet/68")
        return response.body()//Either.Success(Json.decodeFromString(response.body()))
    }
}