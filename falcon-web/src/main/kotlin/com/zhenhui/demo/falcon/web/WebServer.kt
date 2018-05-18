package com.zhenhui.demo.falcon.web

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
open class WebServer : AbstractVerticle() {

    @Autowired
    lateinit var env: Environment

    override fun start() {

        var router = Router.router(vertx)
        router.route("/").handler({c -> c.response().html().end("hello world")})
        router.route("/json").handler({c -> c.response().json().end(Json.encode(Entity("name",32)))})

        vertx.createHttpServer()
                .requestHandler({ handler -> router.accept(handler)})
                .listen(Integer.parseInt(env.getProperty("server.port", "8080")))

    }

    private fun HttpServerResponse.html() : HttpServerResponse {
        return this.putHeader("Content-Type", "text/html; charset=utf-8")
    }

    private fun HttpServerResponse.json() : HttpServerResponse {
        return this.putHeader("Content-Type", "application/json; charset=utf-8")
    }
}