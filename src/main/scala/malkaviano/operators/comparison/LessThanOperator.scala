package malkaviano.operators.comparison

import malkaviano.operators.{BooleanOperator, Operator}

case class LessThanOperator[A : Ordering](operand1: Operator[A], operand2: Operator[A]) extends BooleanOperator {
  import Ordering.Implicits._

  override def evaluate: Boolean = {
    operand1.evaluate < operand2.evaluate
  }
}
