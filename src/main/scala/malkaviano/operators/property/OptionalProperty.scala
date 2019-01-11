package malkaviano.operators.property

import malkaviano.helpers.ReflectionHelper
import malkaviano.operators.Operator

import scala.reflect.ClassTag

case class OptionalProperty[A : ClassTag](property: String, obj: Any) extends Operator[Option[A]] {
  override def evaluate: Option[A] = {
    val result = ReflectionHelper.propertyValue(property, obj)

    result match {
      case v: A => Option(v)
      case v: Option[A] => v
      case _ =>  Option.empty[A]
    }
  }
}
