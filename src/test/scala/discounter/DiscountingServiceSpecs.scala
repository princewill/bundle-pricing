package discounter

import Test._

class DiscountingServiceSpecs extends org.specs2.mutable.Specification {
  /**
   * Assert base and optimization implementations of the DiscountingService
   */
  Seq(new DiscountingService(discounts)) foreach { service => service.getClass.getSimpleName >> {

      "Two bundles, total of $8.15" >> {
        val discount = Request(Seq(apple, apple, mango, banana, grape))

        val request = service.calculate(discount, discounts)

        request.total mustEqual BigDecimal(8.15)
        request.entries must containTheSameElementsAs(Seq(appleAppleBundle, grapeMangoBundle, banana))
      }

      "No bundle, total of $4.50" >> {
        val discount = Request(Seq(apple, grape))

        val request = service.calculate(discount, discounts)

        request.total mustEqual BigDecimal(4.50)
        request.entries must containTheSameElementsAs(Seq(apple, grape))
      }

      "Two same bundles, total of $4.30" >> {
        val discount = Request(Seq(apple, apple, apple, apple))

        val request = service.calculate(discount, discounts)

        request.total mustEqual BigDecimal(4.30)
        request.entries must containTheSameElementsAs(Seq(appleAppleBundle, appleAppleBundle))
      }
    }
  }
}