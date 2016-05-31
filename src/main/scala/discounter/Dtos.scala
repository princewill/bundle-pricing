package discounter

/**
  * Created by princewillohuabunwa on 2016-05-25.
  */

case class CatalogItemDto(name: String)
case class RequestDto(entries: Seq[CatalogItemDto])
case class InvalidRequestException(message: String) extends Exception
