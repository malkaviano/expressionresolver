package malkaviano.operators.property

import malkaviano.helpers.ReflectionHelper
import malkaviano.operators.Operator

case class OptionalNumericProperty(property: String, obj: Any) extends Operator[Option[Int]] {
  override def evaluate: Option[Int] = {
    ReflectionHelper.propertyValue(property, obj).asInstanceOf[Option[Int]]
  }
}
