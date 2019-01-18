package malkaviano.operators.property

import malkaviano.helpers.TestHelpers
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class OptionalPropertySpec extends FunSpec with Matchers  {

  describe("Evaluating") {
    case class Fake(date: Option[DateTime], str: String)

    val date = DateTime.parse("1990-01-01")


    describe("when value exists") {
      describe("when it is an Option field") {
        it("returns the Field") {
          val fake = TestHelpers.proxyingStub(date)

          val expected = Option(date)

          val result = OptionalProperty("date", fake).evaluate

          result shouldBe expected
        }
      }

      describe("when it is not an Option Field") {
        it("returns the Field") {
          val fake = TestHelpers.proxyingStub("wrong")

          val expected = Option("wrong")

          val result =  OptionalProperty("str", fake).evaluate

          result shouldBe expected
        }
      }
    }

    describe("when value does not exist") {
      it("returns None") {
        val fake = TestHelpers.proxyingStub(null)

        val expected = Option.empty[DateTime]

        val result = OptionalProperty("nonexistant", fake).evaluate

        result shouldBe expected
      }
    }
  }
}
