package malkaviano.operators.property

import malkaviano.helpers.ReflectionHelper
import malkaviano.operators.Operator
import org.joda.time.DateTime

case class OptionalDateProperty(property: String, obj: Any) extends Operator[Option[DateTime]] {
  override def evaluate: Option[DateTime] = {
    ReflectionHelper.propertyValue(property, obj).asInstanceOf[Option[DateTime]]
  }
}
