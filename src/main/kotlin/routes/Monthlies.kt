package routes

import database.dbList
import game.CaiLegacyStats
import game.GntLegacyStats
import utils.json.convert
import utils.json.convertAll

external fun require(module: String): dynamic

fun leaderboard(req: dynamic, res: dynamic) {
    val minPlace = js("req.query.from ? parseInt(req.query.from) : 0")
    val maxPlace = js("req.query.to ? parseInt(req.query.to) : 500")

    val db = dbList[req.params.mode.toUpperCase()]
    if(db == undefined) {
        js("res.status(404).json({code: 404, message: 'Mode not found.'})")
        return
    }

    var mode = req.params.mode.toLowerCase()
    if(mode == "bedwars") mode = "bed"
    if(mode == "cai" || mode == "gnt" || mode == "gntm") {
        res.json(emptyArray<Any>())
        return
    }

    val ref = transformRef(db, "monthly", mode)

    ref.orderByChild(transformKey("place", mode)).startAt(minPlace).endAt(maxPlace).once("value").then { snapshot ->
        res.json(transformResultAll(mode, snapshot.`val`()))
    }.catch {e -> println(e)}
}

fun profile(req: dynamic, res: dynamic) {
    val db = dbList[req.params.mode.toUpperCase()]
    if(db == undefined) {
        js("res.status(404).json({code: 404, message: 'Mode not found.'})")
        return
    }
    var mode = req.params.mode.toLowerCase()
    if(mode == "bedwars") mode = "bed"
    if(mode == "gnt" || mode == "gntm") {
        res.json(GntLegacyStats("GNT monthly stats are no longer supported"))
        return
    }
    if(mode == "cai") {
        res.json(CaiLegacyStats("CAI monthly stats are no longer supported"))
        return
    }
    val ref = transformRef(db, "monthly", mode).child(req.params.uuid)
    ref.once("value").then { snapshot ->
        if(snapshot.exists())
            res.status(200).json(transformResult(mode, snapshot.`val`()))
        else res.status(404).json(js("{code: 404, message: 'Player not found.'}"))
    }
}

private fun transformRef(db: dynamic, ref: String, mode: dynamic) : dynamic {
    return if(mode == "bed") db.ref() else db.ref(ref)
}

private fun transformKey(key: String, mode: dynamic) : String {
    return if(mode == "bed") "_____place" else key
}

private fun transformResult(mode: dynamic, input: dynamic) : dynamic {
    return if(mode == "bed") convert(input) else input
}

private fun transformResultAll(mode: dynamic, input: dynamic) : dynamic {
    return if(mode == "bed") convertAll(input) else input
}