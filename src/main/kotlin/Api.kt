import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

class Api {
    private val client = HttpClient(CIO)
    suspend fun get(): Either<String, String> {
        val response = client.get("https://petstore.swagger.io/v2/pet/findByStatus?status=sold")
        return Either.Success(response.body<String>())
    }
}