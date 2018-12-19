package routes

import database.dbList

fun w_lb(req: dynamic, res: dynamic) {

    val mode = req.params.mode.toUpperCase()

    val minPlace = js("req.query.from ? parseInt(req.query.from) : 0")
    val maxPlace = js("req.query.to ? parseInt(req.query.to) : 500")

    val db = dbList["STREAK_BED"]
    db.ref(mode).orderByChild("place").startAt(minPlace).endAt(maxPlace).once("value").then {
        snapshot -> res.status(200).json(snapshot.`val`())
    }


}

fun w_profile(req: dynamic, res: dynamic) {
    val db = dbList["STREAK_BED"]
    db.ref("${req.params.mode.toUpperCase()}/${req.params.uuid}").once("value").then {
        snapshot -> if(snapshot.exists()) res.status(200).json(snapshot.`val`())
                    else res.status(404).json(js("{code: 404, message: 'Player not found.'}"))
    }
}

fun h_lb(req: dynamic, res: dynamic) {
    val mode = req.params.mode.toUpperCase()

    val minPlace = js("req.query.from ? parseInt(req.query.from) : 0")
    val maxPlace = js("req.query.to ? parseInt(req.query.to) : 500")

    val db = dbList["HISTO_BED"]
    db.ref(mode).orderByChild("place").startAt(minPlace).endAt(maxPlace).once("value").then {
        snapshot -> res.status(200).json(snapshot.`val`())
    }
}

fun h_profile(req: dynamic, res: dynamic) {
    val db = dbList["HISTO_BED"]
    db.ref("${req.params.mode.toUpperCase()}/${req.params.uuid}").once("value").then {
        snapshot -> if(snapshot.exists()) res.status(200).json(snapshot.`val`())
    else res.status(404).json(js("{code: 404, message: 'Player not found.'}"))
    }
}