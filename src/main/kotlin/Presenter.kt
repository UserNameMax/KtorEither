import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update

class Presenter(private val useCase: UseCase) {
    private val mutableState = MutableStateFlow("")

    init {
        runBlocking {
            launch {
                val str = when (val response = useCase.get()) {
                    is Either.Success -> response.data.name
                    is Either.Error -> ""
                }
                mutableState.update { str }
            }
            launch {
                mutableState.collect {
                    println(it)
                }
            }
        }
    }

    fun show() {
        runBlocking {
            launch { delay(10000L) }
        }
    }
}