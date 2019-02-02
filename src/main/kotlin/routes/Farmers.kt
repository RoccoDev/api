package routes

import database.dbList

fun kfProfile(req: dynamic, res: dynamic) {
    val mode = req.params.mode.toLowerCase()

    if(mode == "bed" || mode == "bedwars") {
        val db = dbList["FARMERS_BED"]
        db.ref("b/${req.params.uuid}").once("value").then {
            snapshot -> if(snapshot.exists()) {
            val json = snapshot.`val`()

            res.status(200).json(kotlin.js.json("vl" to json.v, "record" to json.k))
        }
        else res.status(404).json(js("{code: 404, message: 'Player not found.'}"))
        }
    }
    else {
        res.status(400).json(js("{code: 400, message: 'Mode not supported.'}"))
    }
}