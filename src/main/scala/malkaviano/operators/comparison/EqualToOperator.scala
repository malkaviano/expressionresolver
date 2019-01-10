package malkaviano.operators.comparison

import malkaviano.operators.{BooleanOperator}

case class EqualToOperator(operand1: Any, operand2: Any) extends BooleanOperator {
  override def evaluate: Boolean = {
    operand1 == operand2
  }
}
