package database

fun database(dbName: String) : dynamic {
    val admin = database.require("firebase-admin")
    val config: dynamic = object{}
    config["credential"] = admin.credential.cert(JSON.parse(database.creds))
    config["databaseURL"] = "https://$dbName.firebaseio.com"
    val app = admin.initializeApp(config, dbName)
    return app.database()
}


