package malkaviano.operators.property

import malkaviano.helpers.ReflectionHelper
import malkaviano.operators.Operator
import org.joda.time.DateTime

case class DateProperty(property: String, obj: Any) extends Operator[DateTime] {
  override def evaluate: DateTime = {
    ReflectionHelper.propertyValue(property, obj).asInstanceOf[DateTime]
  }
}
