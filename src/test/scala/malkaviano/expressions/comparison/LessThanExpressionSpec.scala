package malkaviano.expressions.comparison

import org.scalatest.{FunSpec, Matchers}

class LessThanExpressionSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is true when first operand is less than second operand") {
      val operand1 = 4
      val operand2 = 5

      val expr = LessThanExpression(operand1, operand2)

      expr.evaluate shouldBe true
    }

    it("is false when first operand is equal or greater the second operand") {
      val operand1 = 5.0
      val operand2 = 4

      val expr = LessThanExpression(operand1, operand2)

      expr.evaluate shouldBe false
    }
  }
}
