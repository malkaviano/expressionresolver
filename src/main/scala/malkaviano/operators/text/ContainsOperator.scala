package malkaviano.operators.text

import malkaviano.operators.BooleanOperator

case class ContainsOperator(operand1: Any, operand2: Any) extends BooleanOperator {
  override def evaluate: Boolean = {
    operand1.toString.contains(operand2.toString)
  }
}
