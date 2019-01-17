package malkaviano.operators.property

import malkaviano.operators.Operator
import malkaviano.proxies.Proxying

case class OptionalProperty[A](property: String, obj: Proxying) extends Operator[Option[A]] {
  override def evaluate: Option[A] = {
    obj.valueOf(property)
  }
}
