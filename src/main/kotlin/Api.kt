import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*

class Api {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation)
        install(KtorEitherPlugin)
    }

    suspend fun getPet(): Either<ErrorResponse, Pet> {
        val response = client.get("https://petstore.swagger.io/v2/pet/68")
        return response.body()
    }


}