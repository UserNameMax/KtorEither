package presentation

import domain.UseCase
import network.model.Either

class Presenter(private val useCase: UseCase) {
    suspend fun showName() {
        val str = when (val response = useCase.getPet()) {
            is Either.Success -> response.data.name
            is Either.Error -> response.error.description
        }
        println(str)
    }

    suspend fun showList() {
        val response = useCase.getPets()
        val list = when (response) {
            is Either.Error -> null
            is Either.Success -> response.data.map { it.name }
        }
        if (list == null) {
            println((response as Either.Error).error.description)
        } else {
            println(list)
        }
    }

}