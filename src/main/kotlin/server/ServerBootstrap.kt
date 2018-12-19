package server

external fun require(module: String): dynamic
external val exports: dynamic

fun startServer() : dynamic {
    // Required libraries
    val express = require("express")
    val cors = require("cors")
    val funcs = require("firebase-functions")

    val server = express()
    server.use(cors())

    val api = funcs.https.onRequest(server)
    exports.api = api

    return server
}