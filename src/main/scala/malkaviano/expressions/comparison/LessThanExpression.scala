package malkaviano.expressions.comparison

import malkaviano.expressions.Expression

case class LessThanExpression[A](operand1: A, operand2: A)(implicit ev: A => Ordered[A]) extends Expression {
  override def evaluate: Boolean = {
    operand1 < operand2
  }
}
