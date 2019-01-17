package malkaviano.helpers

import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class ReflectionHelperSpec extends FunSpec with Matchers {
  val date: AnyRef = DateTime.now

  describe("Reflective get value") {
    case class Fake(name: Option[String])

    describe("when field exists") {
      it("returns the property value") {

        val fake = Fake(Some("xpto"))

        val expected = Some("xpto")

        val result = ReflectionHelper.fieldValue("name", fake)

        result shouldBe expected
      }
    }

    describe("when field name cannot be find") {
      it("returns None") {

        val fake = Fake(None)

        val expected = Option.empty[String]

        val result = ReflectionHelper.fieldValue("wrong", fake)

        result shouldBe expected
      }
    }
  }

  describe("Converting from AnyRef to Option") {
    it("returns DateTime") {
      val expected = Option(date)

      val result = ReflectionHelper.anyRefToOption(date)

      result shouldBe expected
    }
  }
}
