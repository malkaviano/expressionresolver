package malkaviano.helpers

import scala.annotation.tailrec
import scala.reflect.ClassTag

object ReflectionHelper {
  def fieldValue(property: String, obj: Any): AnyRef = {
    obj.getClass.getDeclaredFields.find(f => {
      f.setAccessible(true)
      f.getName == property
    }) match {
      case Some(f) => f.get(obj)
      case _ => None
    }
  }

  @tailrec
  def anyRefToOption[A : ClassTag](value: AnyRef): Option[A] = {
    value match {
      case v: Option[_] => {
        try {
          Option(v.get.asInstanceOf[A])
        } catch {
          case _: Throwable => Option.empty[A]
        }
      }
      case v => {
        anyRefToOption(Option(v))
      }
    }
  }
}
