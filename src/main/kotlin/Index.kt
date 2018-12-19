import database.initDbs
import server.addRoutes
import server.startServer


fun main(args: Array<String>) {
    // Init Firebase
    initDbs()
    val server = startServer()
    addRoutes(server)

}