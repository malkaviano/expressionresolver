package malkaviano.helpers

import malkaviano.proxies.Proxying

object TestHelpers {
  def proxyingStub[B](value: B): Proxying = new Proxying {
    override def valueOf[A](name: String): Option[A] = Option(value).asInstanceOf[Option[A]]

    override def setObject(obj: Any): Unit = ???
  }
}
