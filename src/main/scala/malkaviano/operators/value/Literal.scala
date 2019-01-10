package malkaviano.operators.value

import malkaviano.operators.Operator

case class Literal[A](value: A) extends Operator[A] {
  override def evaluate: A = value
}