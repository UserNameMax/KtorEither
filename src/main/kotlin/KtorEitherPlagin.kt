import io.ktor.client.call.*
import io.ktor.client.plugins.api.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json

val KtorEitherPlugin = createClientPlugin("CustomHeaderPlugin") {
    transformResponseBody { response, content, requestedType ->
        if (response.status.value == 200) {
            Either.Success(Json.decodeFromString<Pet>(content.readUTF8Line()!!))
        } else {
            Either.Error("error")
        }
    }
}