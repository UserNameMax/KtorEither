class UseCase(private val api: Api) {
    suspend fun get() = api.getPet()
}