package com.ex_dock.ex_dock.frontend.account

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.eventbus.EventBus
import io.vertx.core.json.Json

class AccountFrontendVerticle: AbstractVerticle() {

  private lateinit var eventBus: EventBus

  override fun start(startPromise: Promise<Void>?) {

  }
}
