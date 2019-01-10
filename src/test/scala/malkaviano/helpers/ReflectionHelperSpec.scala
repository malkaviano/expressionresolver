package malkaviano.helpers

import org.scalatest.{FunSpec, Matchers}

class ReflectionHelperSpec extends FunSpec with Matchers {

  describe("Reflective get value") {
    it("returns the property value") {
      case class Fake(name: Option[String])

      val fake = Fake(None)

      val expected = Option.empty[String]

      val result = ReflectionHelper.propertyValue("name", fake)

      result shouldBe expected
    }
  }
}
