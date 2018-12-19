package server

import routes.*

fun addRoutes(server: dynamic) {
    server.get("/:mode/monthlies/leaderboard") {req, res -> leaderboard(req, res) }
    server.get("/:mode/monthlies/profile/:uuid") {req, res -> profile(req, res) }
    server.get("/:mode/winstreaks/leaderboard", {req, res -> w_lb(req, res)})
    server.get("/:mode/winstreaks/profile/:uuid", {req, res -> w_profile(req, res)})
    server.get("/:mode/winstreaks/historical/leaderboard", {req, res -> h_lb(req, res)})
    server.get("/:mode/winstreaks/historical/profile/:uuid", {req, res -> h_profile(req, res)})
}