package malkaviano.helpers

import org.scalatest.{FunSpec, Matchers}

class ReflectionHelperSpec extends FunSpec with Matchers {

  describe("Reflective get value") {
    case class Fake(name: Option[String])

    describe("when field exists") {
      it("returns the property value") {

        val fake = Fake(Some("xpto"))

        val expected = Some("xpto")

        val result = ReflectionHelper.propertyValue("name", fake)

        result shouldBe expected
      }
    }

    describe("when field name cannot be find") {
      it("returns None") {

        val fake = Fake(None)

        val expected = Option.empty[String]

        val result = ReflectionHelper.propertyValue("wrong", fake)

        result shouldBe expected
      }
    }
  }
}
