package malkaviano.proxies

trait Proxying {
  def valueOf[A](name: String): Option[A]

  def setObject(obj: Any): Unit
}
