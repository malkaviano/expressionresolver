package malkaviano.parsers

import malkaviano.operators.comparison.{EqualToOperator, LessThanOperator}
import malkaviano.operators.logical.AndOperator
import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.value.Literal
import malkaviano.tokens._
import org.scalatest.{FunSpec, Matchers}

class DefaultParserSpec extends FunSpec with Matchers {

  case class Obj(name: String, age: Option[Int])

  val obj = Obj("xpto", Option(10))

  describe("Parsing tokens") {
    it("returns a tree of operators to evaluate") {
      /*
          [
            AndToken
            Less
            Literal 4, "INT"
            Property "age", "INT"
            Eq
            Literal "xpto", "TEXT"
            Property "name", "TEXT"
          ]
       */
      val tokens: Seq[Any] = Seq(
        OperatorToken(Operators.AND),
        OperatorToken(Operators.LESS),
        LiteralToken(4, Tags.NUMBER),
        PropertyToken("age", Tags.NUMBER),
        OperatorToken(Operators.EQ),
        LiteralToken("xpto", Tags.TEXT),
        PropertyToken("name", Tags.TEXT)
      )

      val expected = AndOperator(
        LessThanOperator(
          Literal(4),
          OptionalProperty[Int]("age", obj)
        ),
        EqualToOperator(
          Literal("xpto"),
          OptionalProperty[String]("name", obj)
        )
      )

      val result = (new DefaultParser(obj: Any)).parse(tokens)

      result shouldBe expected
    }
  }
}
