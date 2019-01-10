package malkaviano.operators.property

import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class OptionalPropertySpec extends FunSpec with Matchers  {

  describe("Evaluating") {
    case class Fake(date: Option[DateTime], str: String)

    val date = Some(DateTime.parse("1990-01-01"))
    val fake = Fake(date, "wrong")

    describe("when value exists") {
      describe("when it is a Date") {
        val expected = date

        val result = OptionalProperty[DateTime]("date", fake).evaluate

        result shouldBe expected
      }

      describe("when it is not a Date") {
        an [ClassCastException] should be thrownBy OptionalProperty[DateTime]("str", fake).evaluate
      }
    }

    describe("when value does not exist") {
      val expected = Option.empty[DateTime]

      val result = OptionalProperty[DateTime]("nonexistant", fake).evaluate

      result shouldBe expected
    }
  }
}
