package malkaviano.operators.property

import malkaviano.helpers.ReflectionHelper
import malkaviano.operators.Operator
import org.joda.time.DateTime

case class ValueProperty[A](property: String, obj: Any) extends Operator[A] {
  override def evaluate: A = {
    ReflectionHelper.propertyValue(property, obj).asInstanceOf[A]
  }
}
