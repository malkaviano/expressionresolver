package malkaviano.operators.value

import malkaviano.operators.{Operator}

case class NumericLiteral(value: Int) extends Operator[Int]{
  override def evaluate: Int = value
}