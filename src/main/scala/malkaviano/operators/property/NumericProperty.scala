package malkaviano.operators.property

import malkaviano.helpers.ReflectionHelper
import malkaviano.operators.Operator

case class NumericProperty(property: String, obj: Any) extends Operator[Int] {
  override def evaluate: Int = {
    ReflectionHelper.propertyValue(property, obj).toString.toInt
  }
}
