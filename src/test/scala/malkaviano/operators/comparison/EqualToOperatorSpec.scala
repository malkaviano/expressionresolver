package malkaviano.operators.comparison

import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.value.Literal
import org.scalatest.{FunSpec, Matchers}

class EqualToOperatorSpec extends FunSpec with Matchers {
  describe("Evaluation") {
    case class Fake(name: String, age: Int)

    val fake = Fake("xpto", 10)

    it("is true when both operands have the same value") {
      val operand1 = Literal("xpto")
      val operand2 = OptionalProperty[String]("name", fake)

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe true
    }

    it("is false when if operands don't have the same value") {
      val operand1 = Literal(4)
      val operand2 = OptionalProperty[Int]("age", fake)

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe false
    }
  }
}
