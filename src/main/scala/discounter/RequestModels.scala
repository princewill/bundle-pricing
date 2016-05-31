/**
  * Created by princewillohuabunwa on 2016-05-25.
  */

package discounter

trait RequestEntry {
  def name: String
  def price: BigDecimal
}

case class CatalogItem(name: String, price: BigDecimal) extends RequestEntry

case class Discount(name: String, items: Seq[CatalogItem], price: BigDecimal) extends RequestEntry

case class Request(entries: Seq[RequestEntry]) {

  import org.joda.money.{CurrencyUnit, Money}

  val dollarCad = CurrencyUnit.of("CAD")

  lazy val total: BigDecimal = entries.foldLeft(Money.of(dollarCad, BigDecimal(0).bigDecimal)) {
      (total: Money, requestEntry) => total.plus(Money.of(dollarCad, requestEntry.price.bigDecimal))
    }.getAmount
}