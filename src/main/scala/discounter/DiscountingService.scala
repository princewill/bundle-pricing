/**
  * Created by princewillohuabunwa on 2016-05-25.
  */

package discounter

trait RequestService {
  def calculate(request: Request): Request
}

/**
 * The BundlingService used to get all the combinations of items 
 * and gets the lowest price.
 */
class DiscountingService(bundles: Set[Discount]) extends RequestService {

  def calculate(request: Request) = calculate(request, bundles)

/**
   * calculate used to find the lowest of bundle combinations
   */
  def calculate(request: Request, bundles: Set[Discount]) : Request = {
    val set = combinations(request, bundles)

    if (set.isEmpty)
      request
    else
      set.minBy(_.total)
  }

  /**
   * combinations used to get all combinations.
   */
  def combinations(request: Request, discounts: Set[Discount]): Set[Request] = {
    val discountedOrders = discounts.foldLeft((Set.empty[Request], Set.empty[Discount])) {
      case ((reqs, disc), discount) =>
      applyDiscount(request, discount) match {
        case Some(x) => (reqs + x, disc + discount)
        case None => (reqs, disc)
      }
    }
    discountedOrders match {
      case (requests, discount) => requests.flatMap(request => combinations(request, discount)) ++ requests
    }
  }

 /**
   * applyBundle puts items in a bundle to the shoppingcart, returns None if it can't be applied
   */
  def applyDiscount(request: Request, discount: Discount): Option[Request] = {
    val nonDiscountedEntries = request.entries.diff(discount.items)
    if (nonDiscountedEntries.length == request.entries.length - discount.items.length)
      Some(Request(nonDiscountedEntries :+ discount))
    else
      None
  }
}
