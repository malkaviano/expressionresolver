package malkaviano.proxies

trait Proxying {
  def valueOf[A](name: String): Option[A]
}
