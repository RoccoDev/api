package database

import game.Modes

var dbList : dynamic = null

fun initDbs() {
    dbList = object{}

    Modes.values().forEach {
        if(it != Modes.BED)
        dbList[it] = database(it.dbName)
    }

    dbList["BED"] = dbList["BEDWARS"]

}