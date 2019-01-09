package malkaviano.expressions.logical

import malkaviano.expressions.Expression

case class NotExpression(operand: Expression) extends Expression {
  override def evaluate: Boolean = !operand.evaluate
}
