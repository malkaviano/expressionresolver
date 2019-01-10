package malkaviano.helpers

object ReflectionHelper {
  def propertyValue(property: String, obj: Any): AnyRef = {
    obj.getClass.getDeclaredFields.find(f => {
      f.setAccessible(true)
      f.getName == property
    }) match {
      case Some(f) => f.get(obj)
      case _ => None
    }
  }
}
