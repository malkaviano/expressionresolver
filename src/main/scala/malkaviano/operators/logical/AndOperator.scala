package malkaviano.operators.logical

import malkaviano.operators.{BooleanOperator}

case class AndOperator[A](
                        operand1: BooleanOperator,
                        operand2: BooleanOperator
                        ) extends BooleanOperator {

  override def evaluate: Boolean = {
    operand1.evaluate && operand2.evaluate
  }
}
