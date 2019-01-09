package malkaviano.expressions.comparison

import malkaviano.expressions.Expression

case class EqualToExpression(operand1: Any, operand2: Any) extends Expression {
  override def evaluate: Boolean = {
    operand1 == operand2
  }
}
