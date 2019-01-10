package malkaviano.operators.comparison

import malkaviano.operators.value.Literal
import org.scalatest.{FunSpec, Matchers}

class EqualToOperatorSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is true when both operands have the same value") {
      val operand1 = Literal(5.0)
      val operand2 = Literal(5.0)

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe true
    }

    it("is false when if operands don't have the same value") {
      val operand1 = Literal("xpto")
      val operand2 = Literal("x")

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe false
    }
  }
}
