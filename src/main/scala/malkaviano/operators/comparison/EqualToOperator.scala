package malkaviano.operators.comparison

import malkaviano.operators.{BooleanOperator, Operator}

case class EqualToOperator[A](operand1: Operator[A], operand2:  Operator[A]) extends BooleanOperator {
  override def evaluate: Boolean = {
    operand1.evaluate == operand2.evaluate
  }
}
