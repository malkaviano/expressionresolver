package malkaviano.operators.comparison

import malkaviano.operators.property.{OptionalProperty, ValueProperty}
import malkaviano.operators.value.Literal
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class LessThanOperatorSpec extends FunSpec with Matchers {
  case class Fake(age: Int, birthdate: Option[DateTime] = None)

  val fake = Fake(5)

  describe("Evaluation") {
    it("is true when first operand is less than second operand") {
      val operand1 = Literal(4)
      val operand2 = ValueProperty[Int]("age", fake)

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
        val operand1 = OptionalProperty[DateTime]("birthdate", fake)
        val operand2 = Literal(Option(DateTime.now.plusDays(1)))

        import com.github.nscala_time.time.OrderingImplicits._

        val expr = LessThanOperator(operand1, operand2)

        expr.evaluate shouldBe true
      }
    }
  }
}
