import io.ktor.client.plugins.api.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

val KtorEitherPlugin = createClientPlugin("CustomHeaderPlugin") {
    transformResponseBody { response, content, requestedType ->
        if (response.status.value == 200) {
            Either.Success(
                Json.decodeFromString(
                    serializer(requestedType.kotlinType!!.arguments[1].type!!),
                    content.readUTF8Line()!!
                )
            )
        } else {
            Either.Error(ErrorResponse.getResponse(response.status.value))
        }
    }
}