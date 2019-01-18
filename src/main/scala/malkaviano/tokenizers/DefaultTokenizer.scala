package malkaviano.tokenizers

import malkaviano.tokens.{LiteralToken, OperatorToken, Operators, PropertyToken}
import org.json4s._
import org.json4s.jackson.JsonMethods._

class DefaultTokenizer extends Tokenizer {
  implicit val formats = org.json4s.DefaultFormats

  override def generate(json: String): Seq[Any] = {
    val parsed = parse(json).extract[Map[String, Any]]

    jsonParse(parsed, Seq.empty[Any])
  }

  private def extractToken(oper: String, values: Any, tag: Option[String], tokens: Seq[Any]): Seq[Any] = {
    if (Seq(Operators.AND, Operators.OR, Operators.NOT, Operators.EQ, Operators.LESS).contains(oper)) {
      OperatorToken(oper)

      jsonParse(values, tokens :+ OperatorToken(oper))
    } else if (oper == Operators.LITERAL) {
      tokens ++ Seq(LiteralToken(
        values
          .asInstanceOf[Seq[Any]]
          .head,
        tag.get
      ))
    } else if (oper == Operators.PROPERTY) {
      tokens ++ Seq(PropertyToken(
        values
          .asInstanceOf[Seq[String]]
          .head,
        tag.get
      ))
    } else {
      throw new RuntimeException("Invalid schema")
    }
  }

  private def processMap(map: Map[String, Any], tokens: Seq[Any]): Seq[Any] = {
    map.get("oper") match {
      case Some(oper) => {
        val strOper = oper.toString.toUpperCase
        extractToken(
          strOper,
          map.get("values").get,
          map.get("tag").asInstanceOf[Option[String]],
          tokens
        )
      }
      case _ => {
        throw new RuntimeException("Oper key not found")
      }
    }
  }

  private def jsonParse(parsed: Any, tokens: Seq[Any]): Seq[Any] = {
    parsed match {
      case map: Map[_, _] => {
        val typedMap = map.asInstanceOf[Map[String, Any]]

        processMap(typedMap, tokens)
      }
      case list: Seq[_] => {
        val typedList = list.asInstanceOf[Seq[Map[String, Any]]]

        typedList.foldLeft(tokens)((acc, map) => processMap(map, acc))
      }
    }
  }
}
