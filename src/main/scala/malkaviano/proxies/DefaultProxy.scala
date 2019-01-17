package malkaviano.proxies

import malkaviano.helpers.ReflectionHelper

class DefaultProxy extends Proxying {
  var obj: Option[Any] = Option.empty[Any]

  override def valueOf[A](name: String): Option[A] = {
    obj.flatMap(o => {
      val result = ReflectionHelper.fieldValue(name, o)

      ReflectionHelper.anyRefToOption(result)
    })
  }

  def setObject(obj: Any): Unit = {
    this.obj = Option(obj)
  }
}
