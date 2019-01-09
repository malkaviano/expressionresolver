package malkaviano.expressions.logical

import malkaviano.expressions.Expression
import org.scalatest.{FunSpec, Matchers}

class AndExpressionSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is true when both operand are true") {
      val someExpr = new Expression {
        override def evaluate: Boolean = true
      }

      val expr = AndExpression(someExpr, someExpr)

      expr.evaluate shouldBe true
    }

    it("is false when at least one operand is false") {
      val someExpr1 = new Expression {
        override def evaluate: Boolean = false
      }

      val someExpr2 = new Expression {
        override def evaluate: Boolean = true
      }

      val expr = AndExpression(someExpr1, someExpr2)

      expr.evaluate shouldBe false
    }
  }
}
