package malkaviano.operators.comparison

import org.scalatest.{FunSpec, Matchers}

class EqualToOperatorSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is true when both operands have the same value") {
      val operand1 = 5.0
      val operand2 = 5

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe true
    }

    it("is false when if operands don't have the same value") {
      val operand1 = 5.0
      val operand2 = "x"

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe false
    }
  }
}
