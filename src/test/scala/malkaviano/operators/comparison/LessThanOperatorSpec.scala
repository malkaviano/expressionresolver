package malkaviano.operators.comparison

import malkaviano.helpers.TestHelpers
import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.value.Literal
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class LessThanOperatorSpec extends FunSpec with Matchers {
  val date = DateTime.now

  describe("Evaluation") {
    it("is true when first operand is less than second operand") {
      val fake = TestHelpers.proxyingStub(5)

      val operand1 = Literal(4)
      val operand2 = OptionalProperty("age", fake)

      val expr = LessThanOperator(operand1, operand2)

      expr.evaluate shouldBe true
    }

    it("is false when first operand is equal or greater the second operand") {
      val operand1 = Literal("z")
      val operand2 = Literal("a")

      val expr = LessThanOperator(operand1, operand2)

      expr.evaluate shouldBe false
    }

    describe("when operands are different of types") {
      it("throws exception") {
        val fake = TestHelpers.proxyingStub("xpto")

        val operand1 = Literal(4)
        val operand2 = OptionalProperty("age", fake)

        val expr = LessThanOperator(operand1, operand2)

        an [Exception] should be thrownBy expr.evaluate
      }
    }

    describe("when using custom type with custom Ordering") {
      it("returns true") {
        val fake = TestHelpers.proxyingStub(date)

        val operand1 = OptionalProperty("birthdate", fake)
        val operand2 = Literal(DateTime.now.plusDays(1))

        import com.github.nscala_time.time.OrderingImplicits._

        val expr = LessThanOperator(operand1, operand2)

        expr.evaluate shouldBe true
      }
    }
  }
}
