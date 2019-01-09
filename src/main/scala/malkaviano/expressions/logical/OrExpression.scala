package malkaviano.expressions.logical

import malkaviano.expressions.Expression

case class OrExpression(
                         operand1: Expression,
                         operand2: Expression
                       ) extends Expression {

  override def evaluate: Boolean = {
    operand1.evaluate || operand2.evaluate
  }
}
