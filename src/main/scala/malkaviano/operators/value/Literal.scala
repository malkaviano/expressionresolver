package malkaviano.operators.value

import malkaviano.operators.Operator

case class Literal[A](value: A) extends Operator[Option[A]] {
  override def evaluate: Option[A] = Option(value)
}