package malkaviano.operators.logical

import malkaviano.operators.{BooleanOperator}
import org.scalatest.{FunSpec, Matchers}

class AndOperatorSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is true when both operand are true") {
      val someExpr = new BooleanOperator {
        override def evaluate: Boolean = true
      }

      val expr = AndOperator(someExpr, someExpr)

      expr.evaluate shouldBe true
    }

    it("is false when at least one operand is false") {
      val someExpr1 = new BooleanOperator {
        override def evaluate: Boolean = false
      }

      val someExpr2 = new BooleanOperator {
        override def evaluate: Boolean = true
      }

      val expr = AndOperator(someExpr1, someExpr2)

      expr.evaluate shouldBe false
    }
  }
}
