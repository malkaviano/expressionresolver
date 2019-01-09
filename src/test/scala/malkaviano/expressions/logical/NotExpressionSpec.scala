package malkaviano.expressions.logical

import malkaviano.expressions.Expression
import org.scalatest.{FunSpec, Matchers}

class NotExpressionSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    it("is false when operand is true") {
      val someExpr = new Expression {
        override def evaluate: Boolean = true
      }

      val expr = NotExpression(someExpr)

      expr.evaluate shouldBe false
    }

    it("is true when operand is false") {
      val someExpr = new Expression {
        override def evaluate: Boolean = false
      }

      val expr = NotExpression(someExpr)

      expr.evaluate shouldBe true
    }
  }
}
