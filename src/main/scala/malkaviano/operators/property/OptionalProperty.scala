package malkaviano.operators.property

import malkaviano.helpers.ReflectionHelper
import malkaviano.operators.Operator

case class OptionalProperty[A](property: String, obj: Any) extends Operator[Option[A]] {
  override def evaluate: Option[A] = {
    ReflectionHelper.propertyValue(property, obj).asInstanceOf[Option[A]]
  }
}
