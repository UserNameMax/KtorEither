package domain

import network.Api

class UseCase(private val api: Api) {
    suspend fun getPet() = api.getPet()

    suspend fun getPets() = api.getPets()
}