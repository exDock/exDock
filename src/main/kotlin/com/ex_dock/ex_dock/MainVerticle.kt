package com.ex_dock.ex_dock

import com.ex_dock.ex_dock.backend.enableBackendRouter
import com.ex_dock.ex_dock.database.service.ServerStartException
import com.ex_dock.ex_dock.frontend.account.router.initAccount
import com.ex_dock.ex_dock.frontend.category.router.initCategory
import com.ex_dock.ex_dock.frontend.checkout.router.initCheckout
import com.ex_dock.ex_dock.frontend.home.router.initHome
import com.ex_dock.ex_dock.frontend.product.router.initProduct
import com.ex_dock.ex_dock.frontend.text_pages.router.initTextPages
import com.ex_dock.ex_dock.helper.sendError
import io.github.oshai.kotlinlogging.KotlinLogging
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.eventbus.EventBus
import io.vertx.core.http.CookieSameSite
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.SessionHandler
import io.vertx.ext.web.sstore.SessionStore
import java.util.Properties

class MainVerticle : AbstractVerticle() {
  companion object {
    val logger = KotlinLogging.logger {}
  }

  private val props : Properties = javaClass.classLoader.getResourceAsStream("secret.properties").use {
    Properties().apply { load(it) }
  }

  /**
  * This function is the entry point for the Vert.x application. It starts an HTTP server and listens on port 8888.
  *
  * @param startPromise A [Promise] that will be completed when the HTTP server has started successfully or failed to start.
  *
  * @return Nothing is returned from this function.
  */
  override fun start(startPromise: Promise<Void>) {
    vertx.deployVerticle(ExtensionsLauncher())
      .onSuccess{ _ -> (
        logger.info { "MainVerticle started successfully" }
      )}
      .onFailure { err ->
        logger.error { "Failed to start MainVerticle: $err" }
        startPromise.fail(err)
      }

    val mainRouter : Router = Router.router(vertx)
    val store = SessionStore.create(vertx)
    val sessionHandler = SessionHandler.create(store)
    val eventBus = vertx.eventBus()

    sessionHandler.setCookieSameSite(CookieSameSite.STRICT)

    mainRouter.route().handler(sessionHandler)

    mainRouter.enableBackendRouter(vertx, logger)

    mainRouter.initHome(eventBus)
    mainRouter.initProduct(vertx)
    mainRouter.initCategory(vertx)
    mainRouter.initTextPages(vertx)
    mainRouter.initCheckout(vertx)
    mainRouter.initAccount(vertx)

    vertx
      .createHttpServer(
        HttpServerOptions()
          .setRegisterWebSocketWriteHandlers(true)
      )
      .requestHandler(mainRouter)
      .listen(props.getProperty("FRONTEND_PORT").toInt()) { http ->
        if (http.succeeded()) {
          logger.info { "HTTP server started on port ${props.getProperty("FRONTEND_PORT")}" }
          startPromise.complete()
        } else {
          logger.error { "Failed to start HTTP server: ${http.cause()}" }
          vertx.eventBus().sendError(ServerStartException("Failed to start the HTTP server"))
          startPromise.fail(http.cause())
        }
      }
  }
}
