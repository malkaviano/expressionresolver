package malkaviano.parsers

import malkaviano.operators.comparison.{EqualToOperator, LessThanOperator}
import malkaviano.operators.logical.{AndOperator, NotOperator}
import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.value.Literal
import malkaviano.proxies.{Proxying}
import malkaviano.tokens._
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class DefaultParserSpec extends FunSpec with Matchers {
  val date = DateTime.parse("1990-01-01T00:00:00Z")


  val obj = new Proxying {
    override def valueOf[A](name: String): Option[A] = ???

    override def setObject(obj: Any): Unit = ???
  }

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
          OptionalProperty("age", obj)
        ),
        NotOperator(
          EqualToOperator(
            Literal(date),
            OptionalProperty("birth", obj)
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

    describe("when token type is wrong and cannot be coerced") {
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
