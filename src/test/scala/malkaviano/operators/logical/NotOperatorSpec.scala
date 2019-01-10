package malkaviano.operators.logical

import malkaviano.operators.{BooleanOperator}
import org.scalatest.{FunSpec, Matchers}

class NotOperatorSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is false when operand is true") {
      val someExpr = new BooleanOperator {
        override def evaluate: Boolean = true
      }

      val expr = NotOperator(someExpr)

      expr.evaluate shouldBe false
    }

    it("is true when operand is false") {
      val someExpr = new BooleanOperator {
        override def evaluate: Boolean = false
      }

      val expr = NotOperator(someExpr)

      expr.evaluate shouldBe true
    }
  }
}
