package malkaviano.operators.logical

import malkaviano.operators.{BooleanOperator}

case class NotOperator(operand: BooleanOperator) extends BooleanOperator {
  override def evaluate: Boolean = !operand.evaluate
}
