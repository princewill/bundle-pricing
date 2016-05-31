/**
  * Created by princewillohuabunwa on 2016-05-25.
  */
  
package discounter
import scala.concurrent._
import ExecutionContext.Implicits.global

/**
 * The RequestApi is used to checkout after customer has finished order.
 *
 */
class Api(catalog: Set[CatalogItem], requestService: RequestService) {
   /**
   * checkout is used to get the total amount of order
   *
   */
  def checkout(requestDto: RequestDto): Future[BigDecimal] = {
    val p = Promise[BigDecimal]()
    Future {
      if (validate(requestDto, p)) {
        p.success(requestService.calculate(toRequest(requestDto)).total)
      }
    }
    p.future
  }

  /**
   * toRequest used to check if requested items is in catalog.
    * returns Request
   */
  private def toRequest(requestDto: RequestDto): Request =
    Request(requestDto.entries.map { catalogItemDto =>
      catalog.find(_.name == catalogItemDto.name)
        .getOrElse(throw new Exception(s"Item: ${catalogItemDto.name}, not in catalog."))
    })

  /**
    * validate method to check DTOs and also apply failure events/exceptions
    */
  private def validate(requestDto: RequestDto, p: Promise[BigDecimal]): Boolean = {
    val invalidItems = requestDto.entries.map(_.name).diff(catalog.map(_.name).toSeq)

    if (invalidItems.nonEmpty) {
      p.failure(InvalidRequestException("Your items are not available in our catalog"))
      false
    }
    else if (requestDto.entries.isEmpty) {
      p.failure(InvalidRequestException("Your order contains no items."))
      false
    }
    else true
  }
}