import domain.UseCase
import network.Api
import presentation.Presenter

suspend fun main(args: Array<String>) {
    val api = Api()
    val useCase = UseCase(api)
    val presenter = Presenter(useCase)
    presenter.showName()
    presenter.showList()
}