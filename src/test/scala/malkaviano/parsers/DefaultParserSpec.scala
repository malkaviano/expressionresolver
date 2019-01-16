package malkaviano.parsers

import malkaviano.operators.comparison.{EqualToOperator, LessThanOperator}
import malkaviano.operators.logical.{AndOperator, NotOperator}
import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.value.Literal
import malkaviano.tokens._
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class DefaultParserSpec extends FunSpec with Matchers {
  val date = DateTime.parse("1990-01-01T00:00:00Z")

  case class Obj(name: String, age: Option[Int], birth: DateTime)

  val obj = Obj("xpto", Option(10), date)

  describe("Parsing tokens") {
    it("returns a expression tree to evaluate") {

      val tokens = Seq(
        OperatorToken(Operators.AND),
        OperatorToken(Operators.LESS),
        LiteralToken(4, "number"),
        PropertyToken("age", "number"),
        OperatorToken(Operators.NOT),
        OperatorToken(Operators.EQ),
        LiteralToken("1990-01-01", "date"),
        PropertyToken("birth", "date")
      )

      val expected = AndOperator(
        LessThanOperator(
          Literal(4),
          OptionalProperty[Int]("age", obj)
        ),
        NotOperator(
          EqualToOperator(
            Literal(date),
            OptionalProperty[DateTime]("birth", obj)
          )
        )
      )

      val result = new DefaultParser(obj).parse(tokens)

      result shouldBe expected
    }

    describe("when operator tokens are not of the same type") {
      it("throw a exception") {
        val tokens = Seq(
          OperatorToken(Operators.AND),
          OperatorToken(Operators.LESS),
          LiteralToken(4, "text"),
          PropertyToken("age", "number"),
          OperatorToken(Operators.NOT),
          OperatorToken(Operators.EQ),
          LiteralToken("1990-01-01", "date"),
          PropertyToken("birth", "date")
        )

        an [RuntimeException] should be thrownBy new DefaultParser(obj).parse(tokens)
      }
    }

    describe("when Literal token type is wrong and cannot be coerced") {
      it("throw a exception") {
        val tokens = Seq(
          OperatorToken(Operators.AND),
          OperatorToken(Operators.LESS),
          LiteralToken(4, "date"),
          PropertyToken("age", "number"),
          OperatorToken(Operators.NOT),
          OperatorToken(Operators.EQ),
          LiteralToken("1990-01-01", "date"),
          PropertyToken("birth", "date")
        )

        an [Exception] should be thrownBy new DefaultParser(obj).parse(tokens)
      }
    }
  }
}
