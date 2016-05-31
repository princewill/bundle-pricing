package discounter

object Test {
  val mango = CatalogItem("Mango", BigDecimal(2.50))
  val apple = CatalogItem("Apple", BigDecimal(1.50))
  val bread = CatalogItem("Bread", BigDecimal(1.00))
  val banana = CatalogItem("Banana", BigDecimal(1.00))
  val margarine = CatalogItem("Margarine", BigDecimal(1.00))
  val orange = CatalogItem("Orange", BigDecimal(2.00))
  val grape = CatalogItem("Grape", BigDecimal(3.00))

  val catalog = Set(mango, bread, margarine, apple, orange, banana, grape)

  val mangoAppleBundle = Discount("mango-apple Bundle", Seq(mango, apple), BigDecimal(3.15))
  val breadMargarineBundle = Discount("bread-margarine Bundle", Seq(bread, margarine), BigDecimal(1.80))
  val grapeMangoBundle = Discount("grape-mango Bundle", Seq(grape, mango), BigDecimal(5.00))
  val appleAppleBundle = Discount("apple-apple Bundle", Seq(apple, apple), BigDecimal(2.15))

  val discounts = Set(mangoAppleBundle, breadMargarineBundle, grapeMangoBundle, appleAppleBundle)

  val mangoDto = CatalogItemDto("Apple")
  val orangeDto = CatalogItemDto("Banana")
  val breadDto = CatalogItemDto("Bread")
  val grapeDto = CatalogItemDto("Grape")

  val requestDto = RequestDto(Seq(mangoDto, orangeDto, grapeDto))
}
