package malkaviano.operators.value

import malkaviano.operators.Operator
import org.joda.time.DateTime

case class DateLiteral(value: DateTime) extends Operator[DateTime] {
  override def evaluate: DateTime = value
}
