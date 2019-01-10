package malkaviano.operators.comparison

import malkaviano.operators.value.Literal
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class LessThanOperatorSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is true when first operand is less than second operand") {
      val operand1 = Literal(4)
      val operand2 = Literal(5)

      val expr = LessThanOperator(operand1, operand2)

      expr.evaluate shouldBe true
    }

    it("is false when first operand is equal or greater the second operand") {
      val operand1 = Literal("z")
      val operand2 = Literal("a")

      val expr = LessThanOperator(operand1, operand2)

      expr.evaluate shouldBe false
    }

    describe("when using custom type with custom Ordering") {
      it("returns true") {
        val operand1 = Literal(DateTime.now)
        val operand2 = Literal(DateTime.now.plusDays(1))

        import com.github.nscala_time.time.OrderingImplicits._

        val expr = LessThanOperator(operand1, operand2)

        expr.evaluate shouldBe true
      }
    }
  }
}
