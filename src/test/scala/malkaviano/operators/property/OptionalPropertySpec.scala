package malkaviano.operators.property

import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class OptionalPropertySpec extends FunSpec with Matchers  {

  describe("Evaluating") {
    case class Fake(date: Option[DateTime], str: String)

    val date = Some(DateTime.parse("1990-01-01"))
    val fake = Fake(date, "wrong")

    describe("when value exists") {
      describe("when it is an Option field") {
        it("returns the Date") {
          val expected = date

          val result = OptionalProperty[DateTime]("date", fake).evaluate

          result shouldBe expected
        }
      }

      describe("when it is not an Option Field") {
        it("returns None") {
          val expected = Option("wrong")

          val result =  OptionalProperty[String]("str", fake).evaluate

          result shouldBe expected
        }
      }

      describe("when it is the wrong type") {
        it("returns None") {
          val expected = Option.empty[DateTime]

          val result =  OptionalProperty[DateTime]("str", fake).evaluate

          result shouldBe expected
        }
      }
    }

    describe("when value does not exist") {
      it("returns None") {
        val expected = Option.empty[DateTime]

        val result = OptionalProperty[DateTime]("nonexistant", fake).evaluate

        result shouldBe expected
      }
    }
  }
}
