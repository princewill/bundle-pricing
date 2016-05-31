package discounter

import org.specs2.execute.{Result, AsResult}
import org.specs2.specification.ForEach

import scala.concurrent.{ExecutionContext, Future, Await}
import scala.concurrent.duration.Duration
import ExecutionContext.Implicits.global

import Test._

/**
  * Created by princewillohuabunwa on 2016-05-25.
  */


trait RequestApiContext extends ForEach[Api] {
  def foreach[R: AsResult](f: Api => R): Result = AsResult(f(new Api(catalog, new DiscountingService(discounts))))
}

class RequestApiSpecs extends org.specs2.mutable.Specification with RequestApiContext {

  "Checkout request successfully" >> { api: Api =>
    val checkoutFuture = api.checkout(requestDto)

    Await.result(checkoutFuture, Duration.Inf) mustEqual BigDecimal(5.50)
  }

  "Checkout 100 requests successfully" >> { api: Api =>
    val checkoutFutures = (0 until 100).map(i => api.checkout(requestDto))

    val totals = Await.result(Future.sequence(checkoutFutures), Duration.Inf)

    totals.foreach(_ mustEqual BigDecimal(5.50))
    success
  }

  "Checkout empty request" >> { api: Api =>
    val checkoutFuture = api.checkout(RequestDto(Nil))

    Await.result(checkoutFuture.failed, Duration.Inf) mustEqual
      InvalidRequestException("Your order contains no items.")
  }

  "Checkout request with item not in catalog" >> { api: Api =>
    val checkoutFuture = api.checkout(RequestDto(Seq(CatalogItemDto("Pineapple"))))

    Await.result(checkoutFuture.failed, Duration.Inf) mustEqual
      InvalidRequestException("Your items are not available in our catalog")
  }
}