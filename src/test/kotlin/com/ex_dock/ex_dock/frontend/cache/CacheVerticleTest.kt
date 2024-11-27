package com.ex_dock.ex_dock.frontend.cache

import com.ex_dock.ex_dock.database.JDBCStarter
import com.ex_dock.ex_dock.helper.deployWorkerVerticleHelper
import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(VertxExtension::class)
class CacheVerticleTest {
  private val hitCount = 10000
  private val requestedData = "accounts;categories"

  // This method should not be in the GitHub test, but only be done for manual testing purposes
  // with a populated database
  @Test
  fun testCache(vertx: Vertx, testContext: VertxTestContext) {
    deployWorkerVerticleHelper(vertx, JDBCStarter::class.qualifiedName.toString(), 1, 1).onComplete {
      println("STARTING TESTS")
      val eventBus = vertx.eventBus()
      var counter = 0

      vertx.setPeriodic(1000L) {
        eventBus.request<CacheData>("process.cache.requestData", requestedData).onSuccess {
          println(counter)
          if (counter > hitCount) testContext.completeNow()
          println(it.body())
          counter++
        }.onFailure {
          testContext.failNow(it)
        }
      }
    }
  }
}
