package malkaviano.operators.comparison

import malkaviano.helpers.TestHelpers
import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.value.Literal
import org.scalatest.{FunSpec, Matchers}

class EqualToOperatorSpec extends FunSpec with Matchers {
  describe("Evaluation") {
    it("is true when both operands are equal") {
      val fake = TestHelpers.proxyingStub("xpto")

      val operand1 = Literal("xpto")
      val operand2 = OptionalProperty("name", fake)

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe true
    }

    it("is false when operands are not equal") {
      val fake = TestHelpers.proxyingStub(10)

      val operand1 = Literal(4)
      val operand2 = OptionalProperty("age", fake)

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe false
    }

    it("is false when operands are different") {
      val fake = TestHelpers.proxyingStub("xpto")

      val operand1 = Literal(4)
      val operand2 = OptionalProperty("name", fake)

      val expr = EqualToOperator(operand1, operand2)

      expr.evaluate shouldBe false
    }
  }
}
