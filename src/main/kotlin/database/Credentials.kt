package database

external fun require(module: String): dynamic
external val process: dynamic

val creds : dynamic by lazy {
    val funcs = require("firebase-functions")
    val b64 = require("js-base64").Base64
    b64.decode(funcs.config().fb.json)
}
