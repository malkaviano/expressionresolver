package malkaviano.proxies

import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class DefaultProxySpec extends FunSpec with Matchers {
  val proxy = new DefaultProxy

  val date = DateTime.now

  case class Obj(name: String, age: Option[Int], birth: Option[Option[DateTime]], hobbies: Seq[String])

  describe("Proxying") {
    describe("when no object is being proxied") {
      it("returns None") {
        val expected = Option.empty[Any]

        val result = proxy.valueOf("name")

        result shouldBe expected
      }
    }

    describe("when an object is being proxied") {
      it("returns (Option('xpto'), Option(10), Option(Option(date)), Option(Seq('soccer', 'drums')), None)") {
        val obj = Obj("xpto", Some(10), Option(Option(date)), hobbies = Seq("soccer", "drums"))

        proxy.setObject(obj)

        val expected = (
          Option("xpto"),
          Option(10),
          Option(Option(date)),
          Option(Seq("soccer", "drums")),
          None
        )

        val result = (
          proxy.valueOf("name"),
          proxy.valueOf("age"),
          proxy.valueOf("birth"),
          proxy.valueOf("hobbies"),
          proxy.valueOf("notFound")
        )

        result shouldBe expected
      }
    }
  }
}
