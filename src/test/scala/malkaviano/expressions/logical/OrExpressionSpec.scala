package malkaviano.expressions.logical

import malkaviano.expressions.Expression
import org.scalatest.{FunSpec, Matchers}

class OrExpressionSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is false when both operand are false") {
      val someExpr = new Expression {
        override def evaluate: Boolean = false
      }

      val expr = OrExpression(someExpr, someExpr)

      expr.evaluate shouldBe false
    }

    it("is true when at least one operand is true") {
      val someExpr1 = new Expression {
        override def evaluate: Boolean = false
      }

      val someExpr2 = new Expression {
        override def evaluate: Boolean = true
      }

      val expr = OrExpression(someExpr1, someExpr2)

      expr.evaluate shouldBe true
    }
  }
}
