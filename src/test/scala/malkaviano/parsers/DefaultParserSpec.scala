package malkaviano.parsers

import malkaviano.operators.comparison.{EqualToOperator, LessThanOperator}
import malkaviano.operators.logical.AndOperator
import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.value.Literal
import malkaviano.tokens._
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class DefaultParserSpec extends FunSpec with Matchers {

  case class Obj(name: String, age: Option[Int], birth: DateTime)

  val obj = Obj("xpto", Option(10), DateTime.parse("1990-01-01T00:00:00Z"))

  describe("Parsing tokens") {
    it("returns a tree of operators to evaluate") {

      val tokens = Seq(
        OperatorToken(Operators.AND),
        OperatorToken(Operators.LESS),
        LiteralToken(4, Tags.NUMBER),
        PropertyToken("age", Tags.NUMBER),
        OperatorToken(Operators.EQ),
        LiteralToken("1990-01-01", Tags.DATE),
        PropertyToken("birth", Tags.DATE)
      )

      val expected = AndOperator(
        LessThanOperator(
          Literal(4),
          OptionalProperty[Int]("age", obj)
        ),
        EqualToOperator(
          Literal(DateTime.parse("1990-01-01T00:00:00Z")),
          OptionalProperty[String]("birth", obj)
        )
      )

      val result = DefaultParser(obj).parse(tokens)

      result shouldBe expected
    }
  }
}
