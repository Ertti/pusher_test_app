package com.herokuapp.pushertestapp

import com.zaxxer.hikari.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.features.*
import io.ktor.http.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val hikariConfig = HikariConfig().apply {
    jdbcUrl = System.getenv("JDBC_DATABASE_URL")
}

val dataSource = if (hikariConfig.jdbcUrl != null)
    HikariDataSource(hikariConfig)
else
    HikariDataSource()

val html_utf8 = ContentType.Text.Html.withCharset(Charsets.UTF_8)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(DefaultHeaders)
    install(ConditionalHeaders)



    routing {
        get("/hello") {
            call.respond("hello world kotlin on heroku")
        }
    }

}

