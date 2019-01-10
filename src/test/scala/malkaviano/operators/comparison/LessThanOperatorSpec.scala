package malkaviano.operators.comparison

import org.scalatest.{FunSpec, Matchers}

class LessThanOperatorSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is true when first operand is less than second operand") {
      val operand1 = 4.34
      val operand2 = 5

      val expr = LessThanOperator(operand1, operand2)

      expr.evaluate shouldBe true
    }

    it("is false when first operand is equal or greater the second operand") {
      val operand1 = "z"
      val operand2 = "a"

      val expr = LessThanOperator(operand1, operand2)

      expr.evaluate shouldBe false
    }
  }
}
