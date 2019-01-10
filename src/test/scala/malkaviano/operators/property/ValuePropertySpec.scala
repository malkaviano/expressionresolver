package malkaviano.operators.property

import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class ValuePropertySpec extends FunSpec with Matchers  {

  describe("Evaluating") {
    case class Fake(date: DateTime, str: String)

    val date = DateTime.parse("1990-01-01")
    val fake = Fake(date, "wrong")

    describe("when value exists") {
      describe("when it is a Date") {
        val expected = date

        val result = ValueProperty[DateTime]("date", fake).evaluate

        result shouldBe expected
      }

      describe("when it is not a Date") {
        an [ClassCastException] should be thrownBy ValueProperty[DateTime]("str", fake).evaluate
      }
    }

    describe("when value does not exist") {
      an [ClassCastException] should be thrownBy ValueProperty[DateTime]("str", fake).evaluate
    }
  }
}
