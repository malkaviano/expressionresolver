package malkaviano.operators.logical

import malkaviano.operators.{BooleanOperator}
import org.scalatest.{FunSpec, Matchers}

class OrOperatorSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is false when both operand are false") {
      val someExpr = new BooleanOperator {
        override def evaluate: Boolean = false
      }

      val expr = OrOperator(someExpr, someExpr)

      expr.evaluate shouldBe false
    }

    it("is true when at least one operand is true") {
      val someExpr1 = new BooleanOperator {
        override def evaluate: Boolean = false
      }

      val someExpr2 = new BooleanOperator {
        override def evaluate: Boolean = true
      }

      val expr = OrOperator(someExpr1, someExpr2)

      expr.evaluate shouldBe true
    }
  }
}
