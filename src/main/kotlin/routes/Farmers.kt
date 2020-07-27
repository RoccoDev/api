package routes

import database.dbList
import utils.json.convertAllKf
import kotlin.js.json

fun kfProfile(req: dynamic, res: dynamic) {
    val mode = req.params.mode.toLowerCase()

    if(mode == "bed" || mode == "bedwars") {
        val db = dbList["FARMERS_BED"]
        db.ref("b/${req.params.uuid}").once("value").then {
            snapshot -> if(snapshot.exists()) {
            val json = snapshot.`val`()

            res.status(200).json(kotlin.js.json("vl" to json.v, "record" to json.k, "username" to json.n))
        }
        else res.status(404).json(js("{code: 404, message: 'Player not found.'}"))
        }
    }
    else {
        res.status(400).json(js("{code: 400, message: 'Mode not supported.'}"))
    }
}

fun kfLb(req: dynamic, res: dynamic) {
    val mode = req.params.mode.toLowerCase()

    val max = js("req.query.max ? parseInt(req.query.max) : 500")
    var by = js("req.query.order ? req.query.order : 'record'")

    if(by == "record")
        by = "k"
    else if(by == "vl")
        by = "v"


    if(mode == "bed" || mode == "bedwars") {
        val db = dbList["FARMERS_BED"]
        db.ref("b").orderByChild(by).limitToLast(max).once("value").then {
            snapshot -> if(snapshot.exists()) {
            val json = json()

            js("snapshot.forEach(function(child) {\n" +
                    "json[child.key] = child.val()\n" +
                    "})")

            res.json(convertAllKf(json))
        }
        else res.status(404).json(js("{code: 404, message: 'Leaderboard not found.'}"))
        }
    }
    else {
        res.status(400).json(js("{code: 400, message: 'Mode not supported.'}"))
    }
}