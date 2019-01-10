package malkaviano.operators.comparison

import malkaviano.operators.{BooleanOperator}

case class LessThanOperator[A](operand1: A, operand2: A)(implicit ev: A => Ordered[A]) extends BooleanOperator {
  override def evaluate: Boolean = {
    operand1 < operand2
  }
}
